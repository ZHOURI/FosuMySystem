package cn.honeyjam.dorm.controller;

import cn.honeyjam.dorm.pojo.*;
import cn.honeyjam.dorm.service.impl.StudentServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import tk.mybatis.mapper.util.StringUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;

@Controller
@RequestMapping("stu")
public class StudentController {
    @Autowired
    private StudentServiceImpl service;
    @GetMapping("ownInfo.do")
    public String queryOwnInfo(Model model, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        Student student = (Student)session.getAttribute("user");
        if(StringUtil.isEmpty(student.getId().toString()))
        {
            model.addAttribute("error_msg","未找到该用户");
            return "error";
        }
        student = service.queryOwnInfo(student.getId());
        if(student!=null)
        {
            model.addAttribute("student",student);
            return "student/ownInfo";
        }
        model.addAttribute("error_msg","404");
        return "error";
    }
    @PostMapping("updPass.do")
    public ResponseEntity<Map<String,String>> updatePassword(@RequestParam("password")String password, @SessionAttribute("user") Object user, Model model)
    {
        Student user1 = (Student)user;
        Map<String,String> map = new HashMap<>();
        if(StringUtil.isEmpty(password)||StringUtil.isEmpty(user1.getId().toString()))
        {
            map.put("error_msg","请填写完整的信息");
            map.put("status","false");
        }
        else
        {
            Boolean b = service.updatePassword(password,user1.getId());
            if(b)
            {
                map.put("success_msg","修改成功");
                map.put("status","true");
            }
            else
            {
                map.put("error_msg","修改失败");
                map.put("status","false");
            }
        }
        return ResponseEntity.ok(map);
    }
    @GetMapping("room.do")
    public String queryRoomInfo(Model model,HttpServletRequest request)
    {
        Student student = (Student) request.getSession().getAttribute("user");
        if(StringUtil.isEmpty(student.getRoomId()))
        {
            model.addAttribute("error_msg","201,参数不合法");
            return "error";
        }
        Room room= service.queryRoomInfo(student.getRoomId());
        if(room!=null)
        {
            model.addAttribute("room",room);
            return "student/room";
        }
        model.addAttribute("error_msg","404,未找到信息");
        return "error";
    }
//    @GetMapping("dorm")
//    public String dormInfo(Model model)
//    {
//
//    }
    @PostMapping("stay.do")
    @ResponseBody
    public ResponseEntity<Map<String,String>> stayForm(
            HttpServletRequest request,
            @RequestBody Stay stay
    )
    {
        Map<String,String> map = new HashMap<>();
        Student student = (Student) request.getSession().getAttribute("user");
        stay.setStuId(student.getId());
        if(stay.getEndTime().getTime()< stay.getStartTime().getTime())
        {
            map.put("error","开始时间大于结束时间");
            map.put("status","false");
            return ResponseEntity.ok(map);
        }
        boolean b = service.saveStayForm(stay);
        if(b)
        {
            map.put("success","申请成功，等待审核");
            map.put("status","true");
            return ResponseEntity.ok(map);
        }
        else
        {
            map.put("error","参数错误");
            map.put("status","false");
            return ResponseEntity.ok(map);
        }
    }
    @GetMapping("queryStay.do")
    public String queryStay(
            Model model,
            HttpServletRequest request,
            @RequestParam(name = "currentPage",required = false)Integer currentPage,
            @RequestParam(name = "rows",required = false)Integer rows
            )
    {
        Student student = convert(request);
        Map<String,String[]> map = request.getParameterMap();
        PageResult<Stay> pageResult = service.queryStay(student.getId(),currentPage,rows,map);
       if (pageResult.getItems().size()>0)
       {
           model.addAttribute("pageResult",pageResult);
       }
       else
       {
           model.addAttribute("msg","当前用户还未提交过留宿申请");
       }
        return "student/queryStay";
    }
    @PostMapping("test.do")
    public String test(Model model)
    {
        model.addAttribute("msg","能够解析");
        return "error";
    }

    @RequestMapping("queryRepair.do")
    public String queryRepair(
            Model model,
            HttpServletRequest request,
            @RequestParam(name = "currentPage",required = false)Integer currentPage,
            @RequestParam(name = "rows",required = false)Integer rows)
    {
        Student student = convert(request);
        Repair repair = new Repair();
        repair.setRoomId(student.getRoomId());
        Map<String,String[]> map = request.getParameterMap();
        PageResult<Repair> pageResult = service.queryRepair(repair,currentPage,rows,map);
        if (pageResult.getItems().size()>0)
        {
            model.addAttribute("pageResult",pageResult);
        }
        else
        {
            model.addAttribute("msg","还未有报修记录");
        }
        return "student/queryRepair";
    }

    @RequestMapping("repair.do")
    public ResponseEntity<String> saveRepair(
            @RequestParam("reason")String reason,
            @RequestParam("remarks")String remarks,
            HttpServletRequest request) throws IOException {
        Repair repair = new Repair();
        repair.setReason(reason);
        repair.setRemarks(remarks);
        MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;
//        单个图片文件上传
//        MultipartFile images = mpRequest.getFile("images");
        Map<String, MultipartFile> fileMap = mpRequest.getFileMap();
        String imagesPath = "";
        Collection<MultipartFile> files = fileMap.values();
        for (MultipartFile images:files)
        {
            if(StringUtils.isEmpty(images.getOriginalFilename())||images.getOriginalFilename()==null){
                continue;
            }
            if(!images.getContentType().contains("image"))
            {
                continue;
            }
            System.out.println("上传文件的原始名称：" + images.getOriginalFilename());
            System.out.println("上传文件的类型：" + images.getContentType());
            //单位是字节
            System.out.println("获取上传文件大小：" + images.getSize()+ "字节");
            //保存的路径
            String filePath =request.getSession().getServletContext().getRealPath("/uploads");
            //生成文件的新名字
            String newFileName = UUID.randomUUID() + images.getOriginalFilename();
            //存储图片
            images.transferTo(new File(filePath+"\\"+newFileName));
            //存储在数据库的图片路径，以逗号分隔
            String path = request.getScheme()+"://"+ request.getServerName()+"\\uploads"+"\\"+newFileName;
            imagesPath=imagesPath+path+",";
        }
        repair.setImages(imagesPath);
        Student student = convert(request);
        boolean b = service.saveRepairForm(student,repair);
        if(b)
        {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    private Student convert(HttpServletRequest request)
    {
        Student student = (Student) request.getSession().getAttribute("user");
        return student;
    }

    @RequestMapping("queryNotice.do")
    public String queryNotice(Model model)
    {
        List<Notice> noticeList = service.queryNotice();
        if(noticeList.size()>0)
        {
            model.addAttribute("noticeList",noticeList);
        }
        else
        {
            model.addAttribute("msg","暂无公告");
        }
        return "student/queryNotice";
    }

    @RequestMapping("queryPunish.do")
    public String queryPunish(Model model,HttpServletRequest request)
    {
        Student student = convert(request);
        List<Punish> punishList = service.queryPunish(student.getId());
        if(punishList.size()>0)
        {
            model.addAttribute("punishList",punishList);
        }
        else
        {
            model.addAttribute("msg","暂无违纪记录");
        }
        return "student/queryPunish";
    }

    @RequestMapping("queryFees.do")
    public String queryFees(
            Model model,
            HttpServletRequest request,
            @RequestParam(name = "currentPage",required = false)Integer currentPage,
            @RequestParam(name = "rows",required = false)Integer rows)
    {
        Student student = convert(request);
        Map<String,String[]> map = request.getParameterMap();
        PageResult<Fees> pageResult = service.queryFees(student.getId(),currentPage,rows,map);
        if (pageResult.getItems().size()>0)
        {
            model.addAttribute("pageResult",pageResult);
            model.addAttribute("map",map);
        }
        else
        {
            model.addAttribute("msg","当前用户还未提交过留宿申请");
        }
        return "student/queryFees";
    }
    @RequestMapping("queryRepairOne.do")
    public String queryRepairOne(
            Model model,
            @RequestParam("id")Integer id,
            @RequestParam("currentPage")Integer currentPage
            )
    {
        if(id==null)
        {
            model.addAttribute("msg","参数不能为空");
            return "student/queryRepair";
        }
        Repair repair = service.queryRepairOne(id);
        if(repair!=null)
        {
            model.addAttribute("repair",repair);
            model.addAttribute("currentPage",currentPage);
            return "student/queryRepairOne";
        }
        else
        {
            model.addAttribute("msg","暂无信息");
            return "student/queryRepair";
        }
    }
}
