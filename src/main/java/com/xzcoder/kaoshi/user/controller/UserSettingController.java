package com.xzcoder.kaoshi.user.controller;

import com.xzcoder.kaoshi.admin.service.UserService;
import com.xzcoder.kaoshi.common.po.UserLoginInfoBean;
import com.xzcoder.kaoshi.common.utils.CommonUtils;
import com.xzcoder.kaoshi.common.utils.JSONConvertUtils;
import com.xzcoder.kaoshi.common.utils.ResponseUtils;
import com.xzcoder.kaoshi.configuration.KaoshiProperties;
import com.xzcoder.kaoshi.exception.CustomException;
import com.xzcoder.kaoshi.po.SysUserCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserSettingController {

    @Autowired
    private UserService userService;
    @Autowired
    private KaoshiProperties kaoshiProperties;

    @RequestMapping("/setting.html")
    public String settingHtml(Model model, HttpSession session) throws Exception {
        SysUserCustom user = userService.getUserById(((SysUserCustom) session.getAttribute("user")).getId());
        model.addAttribute("user", user);
        session.setAttribute("user", user);
        return "user/setting";
    }

    @RequestMapping("/updatePwd.action")
    public void updatePwd(HttpSession session, HttpServletResponse response, String lastPwd, String newPwd, String rePwd) throws Exception {
        SysUserCustom lastUser = (SysUserCustom) session.getAttribute("user");
        //验证原密码
        if (!lastPwd.equals(lastUser.getPassword())) {
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("lastPwdError", "原密码不正确！"));
            return;
        }
        //验证两次密码是否相同
        if (!newPwd.equals(rePwd)) {
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("rePwdError", "两次输入密码不相同！"));
            return;
        }
        if ("".equals(newPwd)) {
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("emptyPwdError", "密码不能为空！"));
            return;
        }
        try {
            lastUser.setPassword(newPwd);
            userService.updateUserById(lastUser.getId(), lastUser);
            //获取application域对象
            ServletContext context = session.getServletContext();
            //获取 session中的user对象
            SysUserCustom user = (SysUserCustom) session.getAttribute("user");
            //获取application中的用户map集合
            @SuppressWarnings("unchecked")
            Map<Integer, UserLoginInfoBean> map = (Map<Integer, UserLoginInfoBean>) context.getAttribute("userLoginMap");
            //删除map中的该用户
            map.remove(user.getId());
            //删除session中的用户信息
            session.removeAttribute("user");
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
        } catch (CustomException e) {
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
        }
    }

    @RequestMapping("updateIcon.action")
    public void updateIcon(HttpSession session, HttpServletResponse response,
                           @RequestParam(value = "user_icon", required = true) MultipartFile userIcon) throws Exception {

        SysUserCustom userCustom = (SysUserCustom) session.getAttribute("user");

        try {

            //如果上传了图片
            if (userIcon != null) {
                //图片保存的基本路径
                String basePicPath = kaoshiProperties.getPicPath();

                //根据当天日期进行目录打散
                Date nowDate = new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String childPath = "user/" + df.format(nowDate) + "/";
                File dir = new File(basePicPath + childPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                //图片原名称
                String filename = userIcon.getOriginalFilename();
                if (filename != null && filename.length() > 0) {
                    //通过uuid来保证图片上传的唯一性
                    String newFilename = CommonUtils.uuid() + filename.substring(filename.lastIndexOf("."));

                    //将新的图片路径设置到userQueryVo中
                    userCustom.setIcon(childPath + newFilename);

                    File newFile = new File(basePicPath + childPath + newFilename);
                    newFile.createNewFile();

                    //将图片写入磁盘
                    userIcon.transferTo(newFile);
                }
            }

            //修改用户信息
            userService.updateUserById(userCustom.getId(), userCustom);

            session.setAttribute("user", userCustom);

            ResponseUtils.write(response, "success");
        } catch (CustomException e) {

            ResponseUtils.write(response, "修改密码失败：" + e.getMessage());
        }
    }

}
