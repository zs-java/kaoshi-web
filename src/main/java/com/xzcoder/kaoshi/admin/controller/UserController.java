package com.xzcoder.kaoshi.admin.controller;

import com.xzcoder.kaoshi.admin.service.GroupService;
import com.xzcoder.kaoshi.admin.service.RoleService;
import com.xzcoder.kaoshi.admin.service.UserService;
import com.xzcoder.kaoshi.common.po.PageBean;
import com.xzcoder.kaoshi.common.po.UserLoginInfoBean;
import com.xzcoder.kaoshi.common.utils.CommonUtils;
import com.xzcoder.kaoshi.common.utils.JSONConvertUtils;
import com.xzcoder.kaoshi.common.utils.ResponseUtils;
import com.xzcoder.kaoshi.configuration.KaoshiProperties;
import com.xzcoder.kaoshi.exception.CustomException;
import com.xzcoder.kaoshi.exception.ThrowsException;
import com.xzcoder.kaoshi.exception.UsernameExistsException;
import com.xzcoder.kaoshi.po.SysGroupCustom;
import com.xzcoder.kaoshi.po.SysRoleCustom;
import com.xzcoder.kaoshi.po.SysUserCustom;
import com.xzcoder.kaoshi.vo.UserQueryVo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * UserController
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {

    /**
     * spring自动注入
     */
    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private KaoshiProperties kaoshiProperties;

    /**
     * 转发到用户管理页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "userManager.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String userManager() throws Exception {
        return "admin/userManager";
    }

    /**
     * 组合条件查询用户，返回json数据
     *
     * @param userQueryVo
     * @param pageCode
     * @param rows
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("queryUserList.json")
    public void queryUserList(UserQueryVo userQueryVo,
                              @RequestParam(value = "page", defaultValue = "1") String pageCode,
                              @RequestParam(value = "rows", defaultValue = "15") String rows,
                              @RequestParam(value = "outIds[]", required = false) Integer[] withOutIds,
                              HttpServletResponse response) throws Exception {
        //将分页信息封装到PageBean中
        PageBean page = new PageBean();
        page.setPageCode(Integer.parseInt(pageCode));
        page.setPageSize(Integer.parseInt(rows));

        //将pageBean设置到userQueryVo（用户查询组合条件）中
        userQueryVo.setPageBean(page);

        userQueryVo.setWithOutIds(withOutIds);

        //根据组合查询条件查询用户信息
        List<SysUserCustom> users = userService.findUsersByUserQueryVo(userQueryVo);

        //将查询结果封装成指定的JSON串
        JSONObject result = new JSONObject();
        JSONArray jsonArr = JSONArray.fromObject(users);
        result.put("total", userQueryVo.getPageBean().getTotalRecored());
        result.put("rows", jsonArr);

        //向客户端发送JSON串
        ResponseUtils.write(response, result);
    }

    /**
     * 获取分组树状信息，返回json数据
     */
    @RequestMapping("getAllGroups.json")
    public void getAllGroups(HttpServletResponse response) throws Exception {
        SysGroupCustom groupTree = groupService.getAllGroupsTree();
        String groupsJSON = JSONConvertUtils.groupsTreeToJSON(groupTree);
        System.out.println(groupsJSON);
        ResponseUtils.write(response, groupsJSON);
    }

    /**
     * 获取所有角色信息，返回json数据
     *
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("getAllRoles.json")
    public @ResponseBody List<SysRoleCustom> getAllRoles(HttpServletResponse response) throws Exception {
        return roleService.getAllRoles();
    }

    /**
     * 批量删除用户信息
     *
     * @param response
     * @param ids
     * @throws Exception
     */
    @RequestMapping("deleteUserList.action")
    public void deleteUserList(HttpServletResponse response, @RequestParam("ids[]") Integer[] ids) throws Exception {
        //TODO 判断ids是否为空
        userService.deleteUserList(ids);
        ResponseUtils.write(response, "success");
    }

    /**
     * 删除用户信息
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping("deleteUser.action")
    public String deleteUser(@RequestParam("id") Integer userId) throws Exception {
        userService.deleteUserById(userId);
        return "redirect:userManager.html";
    }

    /**
     * 转发到用户编辑页面
     *
     * @param model
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping("editUser.html")
    public String editUserHtml(Model model, @RequestParam(value = "id", required = true) Integer userId) throws Exception {
        SysUserCustom user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "admin/editUser";
    }

    /**
     * 更新用户信息
     *
     * @param request
     * @param response
     * @param userCustom
     * @param userIcon
     * @param userId
     * @param rpwd
     * @throws Exception
     */
    @RequestMapping("updateUser.action")
    public void updateUser(
            HttpServletRequest request,
            HttpServletResponse response,
            SysUserCustom userCustom,
            @RequestParam(value = "user_icon", required = false) MultipartFile userIcon,
            @RequestParam(value = "id", required = true) Integer userId,
            @RequestParam(value = "rpwd", required = false) String rpwd
    ) throws Exception {
        //服务端验证，验证两次输入密码是否相同
        if (userCustom.getPassword() != null && userCustom.getPassword().length() > 0) {
            if (!rpwd.equals(userCustom.getPassword())) {
                //密码不相同，抛出异常
                throw new CustomException("两次输入密码不一致！");
            }
        }

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
        userService.updateUserById(userId, userCustom);
        ResponseUtils.write(response, "success");
    }

    /**
     * 转发到添加用户页面
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("addUser.html")
    public String addUserHtml(Model model) throws Exception {
        //获取所有角色信息并保存到模型中
        List<SysRoleCustom> rosles = roleService.getAllRoles();
        model.addAttribute("roles", rosles);

        //获取所有用户分组信息保存到模型中
        List<SysGroupCustom> groups = groupService.getAllGroups();
        model.addAttribute("groups", groups);
        return "admin/addUser";
    }

    /**
     * 添加用户
     *
     * @param response
     * @param userCustom
     * @throws Exception
     */
    @RequestMapping("addUser.action")
    public void addUser(HttpServletResponse response, SysUserCustom userCustom) throws Exception {
        try {
            userService.insertUser(userCustom);
            ResponseUtils.write(response, "success");
        } catch (UsernameExistsException e) {
            ResponseUtils.write(response, "usernameExists");
        }
    }

    /**
     * 获取用户在线监控页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("loginOnline.html")
    public String loginOnlineHtml() throws Exception {
        return "admin/loginOnline";
    }

    /**
     * 获取当前登录的用户的信息
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping("loginUserList.json")
    public void loginUserInfo(HttpSession session, HttpServletResponse response) throws Exception {
        ServletContext context = session.getServletContext();

        /*
         * 获取application域中的已经登陆的用户的登陆信息
         * 通过id获取已经登陆的用户的详细信息
         * 返回json数据
         */
        //创建用户信息集合用于保存查找到的所有用户
        List<SysUserCustom> userList = new ArrayList<SysUserCustom>();

        @SuppressWarnings("unchecked")
        Map<Integer, UserLoginInfoBean> map = (Map<Integer, UserLoginInfoBean>) context.getAttribute("userLoginMap");
        //遍历map集合，获取每一个用户的详细信息
        for (Entry<Integer, UserLoginInfoBean> entry : map.entrySet()) {
            Integer key = entry.getKey();
            UserLoginInfoBean loginInfo = entry.getValue();

            //通过用户id获取用户信息
            SysUserCustom user = userService.getUserById(key);
            //将用户的登陆信息设置到user对象中
            user.setLoginInfo(loginInfo);
            //将用户添加到集合中
            userList.add(user);
        }

        //将用户信息转换成指定格式的json数据串返回
        JSONObject result = new JSONObject();
        //设置在线用户总数
        result.put("total", userList.size());
        result.put("rows", JSONArray.fromObject(userList));

        //返回json数据
        ResponseUtils.write(response, result);
    }

    @RequestMapping("logoutUser.action")
    public void logoutUser(HttpServletResponse response,
                           HttpSession session,
                           @RequestParam(value = "id", required = true) Integer userId) throws Exception {
        ServletContext context = session.getServletContext();

        @SuppressWarnings("unchecked")
        Map<Integer, UserLoginInfoBean> map = (Map<Integer, UserLoginInfoBean>) context.getAttribute("userLoginMap");

        //将application域中的该用户信息删除
        if (!map.containsKey(userId)) {
            throw new ThrowsException("该用户未登录！");
        }

        map.remove(userId);

        ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
    }

    @RequestMapping("queryUserListByIds.json")
    public void queryUserListByIds(HttpServletResponse response,
                                   @RequestParam(value = "ids[]", required = false) Integer[] ids,
                                   @RequestParam(value = "groupIds[]", required = false) Integer[] groupIds) throws Exception {
        if (ids == null) {
            ids = new Integer[1];
            ids[0] = 0;
        }
        List<SysUserCustom> userList = userService.findUserListByIds(ids, groupIds);

        //将用户信息转换成指定格式的json数据串返回
        JSONObject result = new JSONObject();
        //设置在线用户总数
        result.put("total", userList.size());
        result.put("rows", JSONArray.fromObject(userList));

        //返回json数据
        ResponseUtils.write(response, result);
    }

    @RequestMapping("queryNotExamUsers.json")
    public void queryNotExamUsers(HttpServletResponse response, UserQueryVo vo,
                                  @RequestParam(value = "ksId", required = true) Integer ksId,
                                  @RequestParam(value = "page", defaultValue = "1") String pageCode,
                                  @RequestParam(value = "rows", defaultValue = "15") String rows) throws Exception {

        //将分页信息封装到PageBean中
        PageBean pageBean = new PageBean();
        pageBean.setPageCode(Integer.parseInt(pageCode));
        pageBean.setPageSize(Integer.parseInt(rows));

        List<SysUserCustom> list = userService.findUserNotExamByKsId(vo, pageBean);

        //获取总记录数
        Integer totalRecored = pageBean.getTotalRecored();

        //转换为json串
        JSONArray arr = JSONArray.fromObject(list);
        JSONObject result = new JSONObject();
        result.put("total", totalRecored);
        result.put("rows", arr);
        //返回json数据
        ResponseUtils.write(response, result);
    }
}
