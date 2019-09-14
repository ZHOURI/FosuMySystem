package cn.honeyjam.dorm.controller;

import cn.honeyjam.dorm.pojo.*;
import cn.honeyjam.dorm.service.impl.ManageServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("man")
public class ManageController {
    @Autowired
    private ManageServiceImpl service;
    private Manager convert(HttpServletRequest request)
    {
        Manager manager = (Manager) request.getSession().getAttribute("user");
        return manager;
    }

    @GetMapping("ownInfo.do")
    public ModelAndView ownInfo(HttpServletRequest request)
    {
        Manager manager = convert(request);
        Manager manager1 = service.queryOwnInfo(manager);
        ModelAndView mv = new ModelAndView();
        if (manager1!=null)
        {
            mv.addObject("manager",manager1);
            mv.setViewName("/manage/ownInfo");
        }
        else
        {
            mv.addObject("error_msg","未找到该用户信息");
            mv.setViewName("error");
        }
        return mv;
    }
    @PostMapping("updPass.do")
    public ResponseEntity<Map<String,String>> updatePassword(
            @RequestParam("password")String password,
            HttpServletRequest request,
            Model model)
    {
        Manager manager = convert(request);
        Map<String,String> map = new HashMap<>();
        if(StringUtil.isEmpty(password)||StringUtil.isEmpty(manager.getId().toString()))
        {
            map.put("error_msg","请填写完整的信息");
            map.put("status","false");
        }
        else
        {
            Boolean b = service.updatePassword(password,manager);
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
    @RequestMapping("queryStu.do")
    public String queryStu(
            HttpServletRequest request,
            Model model,
            @RequestParam(name = "currentPage",required = false)Integer currentPage,
            @RequestParam(name = "rows",required = false)Integer rows)
    {
        Manager manager = convert(request);
        Map<String,String[]> map = request.getParameterMap();
        PageResult<Student> pageResult = service.queryStu(manager,currentPage,rows,map);
        if (pageResult.getItems().size()>0)
        {
            model.addAttribute("pageResult",pageResult);
            model.addAttribute("map",map);
        }
        else
        {
            model.addAttribute("msg","暂无相关信息");
        }
        return "/manage/queryStu";
    }
    @GetMapping("queryStuInfo")
    public String queryStuInfo(@RequestParam("id")Long id,Model model)
    {
        Student student = service.queryStuInfo(id);
        if(student!=null)
        {
            model.addAttribute("student",student);
        }
        else
        {
            model.addAttribute("msg","暂无相关信息");
        }
        return "/manage/queryStuInfo";
    }

    @RequestMapping("queryRoom.do")
    public String queryRoom(
            HttpServletRequest request,
            Model model,
            @RequestParam(name = "currentPage",required = false)Integer currentPage,
            @RequestParam(name = "rows",required = false)Integer rows)
    {
        Manager manager = convert(request);
        Map<String,String[]> map = request.getParameterMap();
        PageResult<Room> pageResult = service.queryRoom(manager,currentPage,rows,map);
        if (pageResult.getItems().size()>0)
        {
            model.addAttribute("pageResult",pageResult);
            model.addAttribute("map",map);
        }
        else
        {
            model.addAttribute("msg","暂无相关信息");
        }
        return "/manage/queryRoom";
    }
    @GetMapping("queryRoomInfo")
    public String queryRoomInfo(@RequestParam("id")String id,Model model)
    {
        Room room = service.queryRoomInfo(id);
        if(room!=null)
        {
            model.addAttribute("room",room);
        }
        else
        {
            model.addAttribute("msg","暂无相关信息");
        }
        return "/manage/queryRoomInfo";
    }
    @RequestMapping("queryWater.do")
    public String queryWater(
            HttpServletRequest request,
            Model model,
            @RequestParam(name = "currentPage",required = false)Integer currentPage,
            @RequestParam(name = "rows",required = false)Integer rows)
    {
        Manager manager = convert(request);
        Map<String,String[]> map = request.getParameterMap();
        PageResult<Water> pageResult = service.queryWater(manager,currentPage,rows,map);
        if (pageResult.getItems().size()>0)
        {
            model.addAttribute("pageResult",pageResult);
            model.addAttribute("map",map);
        }
        else
        {
            model.addAttribute("msg","暂无相关信息");
        }
        return "/manage/queryWater";
    }
    @RequestMapping("queryHealth.do")
    public String queryHealth(
            HttpServletRequest request,
            Model model,
            @RequestParam(name = "currentPage",required = false)Integer currentPage,
            @RequestParam(name = "rows",required = false)Integer rows)
    {
        Manager manager = convert(request);
        Map<String,String[]> map = request.getParameterMap();
        PageResult<Health> pageResult = service.queryHealth(manager,currentPage,rows,map);
        if (pageResult.getItems().size()>0)
        {
            model.addAttribute("pageResult",pageResult);
            model.addAttribute("map",map);
        }
        else
        {
            model.addAttribute("msg","暂无相关信息");
        }
        return "/manage/queryHealth";
    }
    @RequestMapping("queryFees.do")
    public String queryFees(
            HttpServletRequest request,
            Model model,
            @RequestParam(name = "currentPage",required = false)Integer currentPage,
            @RequestParam(name = "rows",required = false)Integer rows)
    {
        Manager manager = convert(request);
        Map<String,String[]> map = request.getParameterMap();
        PageResult<Fees> pageResult = service.queryFees(manager,currentPage,rows,map);
        if (pageResult.getItems().size()>0)
        {
            model.addAttribute("pageResult",pageResult);
            model.addAttribute("map",map);
        }
        else
        {
            model.addAttribute("msg","暂无相关信息");
        }
        return "/manage/queryFees";
    }
    @RequestMapping("queryPunish.do")
    public String queryPunish(
            HttpServletRequest request,
            Model model,
            @RequestParam(name = "currentPage",required = false)Integer currentPage,
            @RequestParam(name = "rows",required = false)Integer rows)
    {
        Manager manager = convert(request);
        Map<String,String[]> map = request.getParameterMap();
        PageResult<Punish> pageResult = service.queryPunish(manager,currentPage,rows,map);
        if (pageResult.getItems().size()>0)
        {
            model.addAttribute("pageResult",pageResult);
            model.addAttribute("map",map);
        }
        else
        {
            model.addAttribute("msg","暂无相关信息");
        }
        return "/manage/queryPunish";
    }
    @RequestMapping("queryStay.do")
    public String queryStay(
            HttpServletRequest request,
            Model model,
            @RequestParam(name = "currentPage",required = false)Integer currentPage,
            @RequestParam(name = "rows",required = false)Integer rows)
    {
        Manager manager = convert(request);
        Map<String,String[]> map = request.getParameterMap();
        PageResult<Stay> pageResult = service.queryStay(manager,currentPage,rows,map);
        if (pageResult.getItems().size()>0)
        {
            model.addAttribute("pageResult",pageResult);
            model.addAttribute("map",map);
        }
        else
        {
            model.addAttribute("msg","暂无相关信息");
        }
        return "/manage/queryStay";
    }
    @RequestMapping("queryRepair.do")
    public String queryRepair(
            HttpServletRequest request,
            Model model,
            @RequestParam(name = "currentPage",required = false)Integer currentPage,
            @RequestParam(name = "rows",required = false)Integer rows)
    {
        Manager manager = convert(request);
        Map<String,String[]> map = request.getParameterMap();
        PageResult<Repair> pageResult = service.queryRepair(manager,currentPage,rows,map);
        if (pageResult.getItems().size()>0)
        {
            model.addAttribute("pageResult",pageResult);
            model.addAttribute("map",map);
        }
        else
        {
            model.addAttribute("msg","暂无相关信息");
        }
        return "/manage/queryRepair";
    }
    @RequestMapping("queryNotice.do")
    public String queryNotice(
            HttpServletRequest request,
            Model model,
            @RequestParam(name = "currentPage",required = false)Integer currentPage,
            @RequestParam(name = "rows",required = false)Integer rows)
    {
        Manager manager = convert(request);
        Map<String,String[]> map = request.getParameterMap();
        PageResult<Notice> pageResult = service.queryNotice(manager,currentPage,rows,map);
        if (pageResult.getItems().size()>0)
        {
            model.addAttribute("pageResult",pageResult);
            model.addAttribute("map",map);
        }
        else
        {
            model.addAttribute("msg","暂无相关信息");
        }
        return "/manage/queryNotice";
    }
    @RequestMapping("queryVisitor.do")
    public String queryVisitor(
            HttpServletRequest request,
            Model model,
            @RequestParam(name = "currentPage",required = false)Integer currentPage,
            @RequestParam(name = "rows",required = false)Integer rows)
    {
        Manager manager = convert(request);
        Map<String,String[]> map = request.getParameterMap();
        PageResult<Visitor> pageResult = service.queryVisitor(manager,currentPage,rows,map);
        if (pageResult.getItems().size()>0)
        {
            model.addAttribute("pageResult",pageResult);
            model.addAttribute("map",map);
        }
        else
        {
            model.addAttribute("msg","暂无相关信息");
        }
        return "/manage/queryVisitor";
    }
    @RequestMapping("queryRoomPunish.do")
    public String queryRoomPunish(
            HttpServletRequest request,
            Model model,
            @RequestParam(name = "currentPage",required = false)Integer currentPage,
            @RequestParam(name = "rows",required = false)Integer rows)
    {
        Manager manager = convert(request);
        Map<String,String[]> map = request.getParameterMap();
        PageResult<RoomPunish> pageResult = service.queryRoomPunish(manager,currentPage,rows,map);
        if (pageResult.getItems().size()>0)
        {
            model.addAttribute("pageResult",pageResult);
            model.addAttribute("map",map);
        }
        else
        {
            model.addAttribute("msg","暂无相关信息");
        }
        return "/manage/queryRoomPunish";
    }
    @RequestMapping("editStay.do")
    @ResponseBody
    public ResponseEntity<Map<String,String>> editStay(@RequestParam("id")Integer id)
    {
        if(id==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Boolean b = service.editStay(id);
        Map<String,String> map = new HashMap<>();
        if(b)
        {
            map.put("status","true");
            map.put("msg","操作成功");
        }
        else
        {
            map.put("status","false");
            map.put("msg","操作失败");
        }
        return ResponseEntity.ok(map);
    }


    @RequestMapping("editRepair.do")
    public ResponseEntity<Map<String,String>> editRepair(@RequestParam("id")Integer id)
    {
        if(id==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Boolean b = service.editRepair(id);
        Map<String,String> map = new HashMap<>();
        if(b)
        {
            map.put("status","true");
            map.put("msg","操作成功");
        }
        else
        {
            map.put("status","false");
            map.put("msg","操作失败");
        }
        return ResponseEntity.ok(map);
    }
    @RequestMapping("addStuPunish.do")
    @ResponseBody
    public ResponseEntity<Map<String,String>> addStuPunish(
            HttpServletRequest request,
            @RequestBody Punish punish
    )
    {
        Map<String,String> map = new HashMap<>();
        Boolean b = service.addStuPunish(punish);
        if(b)
        {
            map.put("success","添加成功");
            map.put("status","true");
        }
        else
        {
            map.put("error","添加失败");
            map.put("status","false");
        }
        return ResponseEntity.ok(map);
    }
    @RequestMapping("editFees.do")
    @ResponseBody
    public ResponseEntity<Map<String,String>> editFees(@RequestParam("id")Integer id)
    {
        if(id==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Boolean b = service.editFees(id);
        Map<String,String> map = new HashMap<>();
        if(b)
        {
            map.put("status","true");
            map.put("msg","操作成功");
        }
        else
        {
            map.put("status","false");
            map.put("msg","操作失败");
        }
        return ResponseEntity.ok(map);
    }
    @RequestMapping("delWater.do")
    public ResponseEntity<Map<String,String>> delWater(@RequestParam("id")Integer id)
    {
        if(id==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Boolean b = service.delWater(id);
        Map<String,String> map = new HashMap<>();
        if(b)
        {
            map.put("status","true");
            map.put("msg","删除成功");
        }
        else
        {
            map.put("status","false");
            map.put("msg","删除失败");
        }
        return ResponseEntity.ok(map);
    }
    @RequestMapping("delStuFees.do")
    public ResponseEntity<Map<String,String>> delStuFees(@RequestParam("id")Integer id)
    {
        if(id==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Boolean b = service.delStuFees(id);
        Map<String,String> map = new HashMap<>();
        if(b)
        {
            map.put("status","true");
            map.put("msg","删除成功");
        }
        else
        {
            map.put("status","false");
            map.put("msg","删除失败");
        }
        return ResponseEntity.ok(map);
    }
    @RequestMapping("delStay.do")
    public ResponseEntity<Map<String,String>> delStay(@RequestParam("id")Integer id)
    {
        if(id==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Boolean b = service.delStay(id);
        Map<String,String> map = new HashMap<>();
        if(b)
        {
            map.put("status","true");
            map.put("msg","删除成功");
        }
        else
        {
            map.put("status","false");
            map.put("msg","删除失败");
        }
        return ResponseEntity.ok(map);
    }
    @RequestMapping("addRoomPunish.do")
    @ResponseBody
    public ResponseEntity<Map<String,String>> addRoomPunish(
            HttpServletRequest request,
            @RequestBody RoomPunish roomPunish
    )
    {
        Map<String,String> map = new HashMap<>();
        Boolean b = service.addRoomPunish(roomPunish);
        if(b)
        {
            map.put("success","添加成功");
            map.put("status","true");
        }
        else
        {
            map.put("error","添加失败");
            map.put("status","false");
        }
        return ResponseEntity.ok(map);
    }
    @RequestMapping("editWater.do")
    public ResponseEntity<Map<String,String>> editWater(@RequestParam("id")Integer id)
    {
        if(id==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Boolean b = service.editWater(id);
        Map<String,String> map = new HashMap<>();
        if(b)
        {
            map.put("status","true");
            map.put("msg","操作成功");
        }
        else
        {
            map.put("status","false");
            map.put("msg","操作失败");
        }
        return ResponseEntity.ok(map);
    }
    @PostMapping("addRoomHealth.do")
    @ResponseBody
    public ResponseEntity<Map<String,String>> addRoomPunish(
            HttpServletRequest request,
            @RequestBody Health health
    )
    {
        Map<String,String> map = new HashMap<>();
        Boolean b = service.addRoomHealth(health);
        if(b)
        {
            map.put("success","添加成功");
            map.put("status","true");
        }
        else
        {
            map.put("error","添加失败");
            map.put("status","false");
        }
        return ResponseEntity.ok(map);
    }
    @PostMapping("addRoomWater.do")
    @ResponseBody
    public ResponseEntity<Map<String,String>> addRoomWater(
            HttpServletRequest request,
            @RequestBody Water water
    )
    {
        Map<String,String> map = new HashMap<>();
        Boolean b = service.addRoomWater(water);
        if(b)
        {
            map.put("success","添加成功");
            map.put("status","true");
        }
        else
        {
            map.put("error","添加失败");
            map.put("status","false");
        }
        return ResponseEntity.ok(map);
    }
    @RequestMapping("delStuPunish.do")
    public ResponseEntity<Map<String,String>> delStuPunish(@RequestParam("id")Integer id)
    {
        if(id==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Boolean b = service.delStuPunish(id);
        Map<String,String> map = new HashMap<>();
        if(b)
        {
            map.put("status","true");
            map.put("msg","删除成功");
        }
        else
        {
            map.put("status","false");
            map.put("msg","删除失败");
        }
        return ResponseEntity.ok(map);
    }
    @RequestMapping("delVisitor.do")
    public ResponseEntity<Map<String,String>> delVisitor(@RequestParam("id")Integer id)
    {
        if(id==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Boolean b = service.delVisitor(id);
        Map<String,String> map = new HashMap<>();
        if(b)
        {
            map.put("status","true");
            map.put("msg","删除成功");
        }
        else
        {
            map.put("status","false");
            map.put("msg","删除失败");
        }
        return ResponseEntity.ok(map);
    }
    @RequestMapping("delNotice.do")
    public ResponseEntity<Map<String,String>> delNotice(@RequestParam("id")Integer id)
    {
        if(id==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Boolean b = service.delNotice(id);
        Map<String,String> map = new HashMap<>();
        if(b)
        {
            map.put("status","true");
            map.put("msg","删除成功");
        }
        else
        {
            map.put("status","false");
            map.put("msg","删除失败");
        }
        return ResponseEntity.ok(map);
    }
    @RequestMapping("delHealth.do")
    public ResponseEntity<Map<String,String>> delHealth(@RequestParam("id")Integer id)
    {
        if(id==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Boolean b = service.delHealth(id);
        Map<String,String> map = new HashMap<>();
        if(b)
        {
            map.put("status","true");
            map.put("msg","删除成功");
        }
        else
        {
            map.put("status","false");
            map.put("msg","删除失败");
        }
        return ResponseEntity.ok(map);
    }
    @RequestMapping("delRoomPunish.do")
    public ResponseEntity<Map<String,String>> delRoomPunish(@RequestParam("id")Integer id)
    {
        if(id==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Boolean b = service.delRoomPunish(id);
        Map<String,String> map = new HashMap<>();
        if(b)
        {
            map.put("status","true");
            map.put("msg","删除成功");
        }
        else
        {
            map.put("status","false");
            map.put("msg","删除失败");
        }
        return ResponseEntity.ok(map);
    }
    @RequestMapping("queryStuPunishOne.do")
    public String queryStuPunishOne(@RequestParam("id")Integer id,Model model)
    {
        Punish punish= service.queryStuPunishOne(id);
        if (punish!=null)
        {
            model.addAttribute("punish",punish);
            return "/manage/updStuPunish";
        }
        else
        {
            model.addAttribute("msg","暂无相关信息");
            return "/error";
        }
    }
    @RequestMapping("queryFeesOne.do")
    public String queryFeesOne(@RequestParam("id")Integer id,Model model)
    {
        Fees fees= service.queryFeesOne(id);
        if (fees!=null)
        {
            model.addAttribute("fees",fees);
            return "/manage/updFees";
        }
        else
        {
            model.addAttribute("msg","暂无相关信息");
            return "/error";
        }
    }
    @RequestMapping("queryWaterOne.do")
    public String queryWaterOne(@RequestParam("id")Integer id,Model model)
    {
        Water water= service.queryWaterOne(id);
        if (water!=null)
        {
            model.addAttribute("water",water);
            return "/manage/updWater";
        }
        else
        {
            model.addAttribute("msg","暂无相关信息");
            return "/error";
        }
    }
    @RequestMapping("queryRoomPunishOne.do")
    public String queryRoomPunishOne(@RequestParam("id")Integer id,Model model)
    {
        RoomPunish roomPunish= service.queryRoomPunishOne(id);
        if (roomPunish!=null)
        {
            model.addAttribute("roomPunish",roomPunish);
            return "/manage/updRoomPunish";
        }
        else
        {
            model.addAttribute("msg","暂无相关信息");
            return "/error";
        }
    }
    @RequestMapping("queryHealthOne.do")
    public String queryHealthOne(@RequestParam("id")Integer id,Model model)
    {
        Health health= service.queryHealthOne(id);
        if (health!=null)
        {
            model.addAttribute("health",health);
            return "/manage/updHealth";
        }
        else
        {
            model.addAttribute("msg","暂无相关信息");
            return "/error";
        }
    }
    @RequestMapping("queryVisitorOne.do")
    public String queryVisitorOne(@RequestParam("id")Integer id,Model model)
    {
        Visitor visitor= service.queryVisitorOne(id);
        if (visitor!=null)
        {
            model.addAttribute("visitor",visitor);
            return "/manage/updVisitor";
        }
        else
        {
            model.addAttribute("msg","暂无相关信息");
            return "/error";
        }
    }
    @RequestMapping("queryNoticeOne.do")
    public String queryNoticeOne(@RequestParam("id")Integer id,Model model)
    {
        Notice notice= service.queryNoticeOne(id);
        if (notice!=null)
        {
            model.addAttribute("notice",notice);
            return "/manage/updNotice";
        }
        else
        {
            model.addAttribute("msg","暂无相关信息");
            return "/error";
        }
    }
    @RequestMapping("updStuPunish.do")
    @ResponseBody
    public ResponseEntity<Map<String,String>> updStuPunish(
            HttpServletRequest request,
            @RequestBody Punish punish
    )
    {
        Map<String,String> map = new HashMap<>();
        Boolean b = service.updStuPunish(punish);
        if(b)
        {
            map.put("success","修改成功");
            map.put("status","true");
        }
        else
        {
            map.put("error","修改失败");
            map.put("status","false");
        }
        return ResponseEntity.ok(map);
    }
    @RequestMapping("updFees.do")
    @ResponseBody
    public ResponseEntity<Map<String,String>> updFees(
            HttpServletRequest request,
            @RequestBody Fees fees
    )
    {
        Map<String,String> map = new HashMap<>();
        Boolean b = service.updFees(fees);
        if(b)
        {
            map.put("success","修改成功");
            map.put("status","true");
        }
        else
        {
            map.put("error","修改失败");
            map.put("status","false");
        }
        return ResponseEntity.ok(map);
    }
    @RequestMapping("updWater.do")
    @ResponseBody
    public ResponseEntity<Map<String,String>> updWater(
            HttpServletRequest request,
            @RequestBody Water water
    )
    {
        Map<String,String> map = new HashMap<>();
        Boolean b = service.updWater(water);
        if(b)
        {
            map.put("success","修改成功");
            map.put("status","true");
        }
        else
        {
            map.put("error","修改失败");
            map.put("status","false");
        }
        return ResponseEntity.ok(map);
    }
    @RequestMapping("updRoomPunish.do")
    @ResponseBody
    public ResponseEntity<Map<String,String>> updRoomPunish(
            HttpServletRequest request,
            @RequestBody RoomPunish roomPunish
    )
    {
        Map<String,String> map = new HashMap<>();
        Boolean b = service.updRoomPunish(roomPunish);
        if(b)
        {
            map.put("success","修改成功");
            map.put("status","true");
        }
        else
        {
            map.put("error","修改失败");
            map.put("status","false");
        }
        return ResponseEntity.ok(map);
    }
    @RequestMapping("updHealth.do")
    @ResponseBody
    public ResponseEntity<Map<String,String>> updHealth(
            HttpServletRequest request,
            @RequestBody Health health
    )
    {
        Map<String,String> map = new HashMap<>();
        Boolean b = service.updHealth(health);
        if(b)
        {
            map.put("success","修改成功");
            map.put("status","true");
        }
        else
        {
            map.put("error","修改失败");
            map.put("status","false");
        }
        return ResponseEntity.ok(map);
    }
    @RequestMapping("updVisitor.do")
    @ResponseBody
    public ResponseEntity<Map<String,String>> updVisitor(
            HttpServletRequest request,
            @RequestBody Visitor visitor
    )
    {
        Map<String,String> map = new HashMap<>();
        Boolean b = service.updVisitor(visitor);
        if(b)
        {
            map.put("success","修改成功");
            map.put("status","true");
        }
        else
        {
            map.put("error","修改失败");
            map.put("status","false");
        }
        return ResponseEntity.ok(map);
    }
    @RequestMapping("updRepair.do")
    @ResponseBody
    public ResponseEntity<Map<String,String>> updRepair(
            HttpServletRequest request,
            @RequestBody Repair repair
    )
    {
        Map<String,String> map = new HashMap<>();
        Boolean b = service.updRepair(repair);
        if(b)
        {
            map.put("success","修改成功");
            map.put("status","true");
        }
        else
        {
            map.put("error","修改失败");
            map.put("status","false");
        }
        return ResponseEntity.ok(map);
    }
    @RequestMapping("updNotice.do")
    @ResponseBody
    public ResponseEntity<Map<String,String>> updNotice(
            HttpServletRequest request,
            @RequestBody Notice notice
    )
    {
        Map<String,String> map = new HashMap<>();
        Boolean b = service.updNotice(notice);
        if(b)
        {
            map.put("success","修改成功");
            map.put("status","true");
        }
        else
        {
            map.put("error","修改失败");
            map.put("status","false");
        }
        return ResponseEntity.ok(map);
    }
    @RequestMapping("addNotice.do")
    @ResponseBody
    public ResponseEntity<Map<String,String>> addNotice(
            HttpServletRequest request,
            @RequestBody Notice notice
    )
    {
        Map<String,String> map = new HashMap<>();
        Manager manager = convert(request);
        Boolean b = service.addNotice(notice,manager.getDormId());
        if(b)
        {
            map.put("success","添加成功");
            map.put("status","true");
        }
        else
        {
            map.put("error","添加失败");
            map.put("status","false");
        }
        return ResponseEntity.ok(map);
    }
    @RequestMapping("addFees.do")
    @ResponseBody
    public ResponseEntity<Map<String,String>> addFees(
            HttpServletRequest request,
            @RequestBody Fees fees
    )
    {
        Map<String,String> map = new HashMap<>();
        Boolean b = service.addFees(fees);
        if(b)
        {
            map.put("success","添加成功");
            map.put("status","true");
        }
        else
        {
            map.put("error","添加失败");
            map.put("status","false");
        }
        return ResponseEntity.ok(map);
    }
    @RequestMapping("addHealth.do")
    @ResponseBody
    public ResponseEntity<Map<String,String>> addHealth(
            HttpServletRequest request,
            @RequestBody Health health
    )
    {
        Map<String,String> map = new HashMap<>();
        Boolean b = service.addHealth(health);
        if(b)
        {
            map.put("success","添加成功");
            map.put("status","true");
        }
        else
        {
            map.put("error","添加失败");
            map.put("status","false");
        }
        return ResponseEntity.ok(map);
    }
    @RequestMapping("addVisitor.do")
    @ResponseBody
    public ResponseEntity<Map<String,String>> addVisitor(
            HttpServletRequest request,
            @RequestBody Visitor visitor
    )
    {
        Map<String,String> map = new HashMap<>();
        Manager manager = convert(request);
        Boolean b = service.addVisitor(visitor,manager.getDormId());
        if(b)
        {
            map.put("success","添加成功");
            map.put("status","true");
        }
        else
        {
            map.put("error","添加失败");
            map.put("status","false");
        }
        return ResponseEntity.ok(map);
    }
    @RequestMapping("addWater.do")
    @ResponseBody
    public ResponseEntity<Map<String,String>> addWater(
            HttpServletRequest request,
            @RequestBody Water water
    )
    {
        Map<String,String> map = new HashMap<>();
        Boolean b = service.addWater(water);
        if(b)
        {
            map.put("success","添加成功");
            map.put("status","true");
        }
        else
        {
            map.put("error","添加失败");
            map.put("status","false");
        }
        return ResponseEntity.ok(map);
    }

}

