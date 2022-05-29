package com.atlx.controller;

import com.atlx.pojo.ScenicOrder;
import com.atlx.pojo.User;
import com.atlx.pojo.resp.RestBean;
import com.atlx.service.IUserService;
import com.atlx.utils.EmailVerify;
import com.atlx.utils.ValidateUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;


@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    IUserService iUserService;
    @Autowired
    ValidateUtil validateUtil;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    EmailVerify emailVerify;

    //图片验证码
    @Resource
    private DefaultKaptcha captchaProducer;
    public static final String LOGIN_VALIDATE_CODE = "login_validate_code";

    /**
     * 登录验证
     * @param user
     * @return
     */
    @RequestMapping("/toLogin")
    public RestBean login(@RequestBody Map user){

        String account = (String)user.get("account");
        String password = (String)user.get("password");
        String code = user.get("code").toString().toUpperCase();

        ValueOperations valueOperations = redisTemplate.opsForValue();
        String loginValidateCode = (String)valueOperations.get(LOGIN_VALIDATE_CODE);

        if(loginValidateCode == null){
            return new RestBean(500,"验证码已过期",null);

        } else if(!loginValidateCode.equals(code)){
            return new RestBean(500,"验证码错误",null);
        }


        //shiro认证登录
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token =new UsernamePasswordToken(account,password);

        try {
            subject.login(token);
            return new RestBean(200,"登陆成功",account);
        }catch (Exception e){
            System.out.println("登录认证时报错："+e);
            return new RestBean(500,"用户名或密码错误",null);
        }
    }


    /**
     * 登录验证码图片
     **/
    @GetMapping(value = {"/loginValidateCode"})
    public void loginValidateCode(HttpServletResponse response) throws Exception{
        validateUtil.validateCode(response,captchaProducer,LOGIN_VALIDATE_CODE);
    }



    /**
     * 注册
     * @param registerParam
     * @return
     */
    @PostMapping("/register")
    public RestBean<Void> register(@RequestBody Map registerParam){

        String account = (String)registerParam.get("account");
        String password = (String)registerParam.get("password");
        String email = (String)registerParam.get("email");
        String emailCode = (String)registerParam.get("emailCode");

        //邮件验证码验证过程
        String registerEmailCode = redisTemplate.opsForValue().get("verifyCode:"+email);
        if(registerEmailCode == null){
            return new RestBean(500,"验证码已过期",null);

        } else if(!registerEmailCode.equals(emailCode)){
            return new RestBean(500,"验证码错误",null);
        }

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setEmail(email);

        if(iUserService.register(user)){
            return new RestBean(200,"注册成功",null);
        }

        return new RestBean(500,"注册失败",null);
    }

    /**
     * 忘记密码
     * @param forgetParam
     * @return
     */
    @PostMapping("/forget")
    public RestBean<Void> forget(@RequestBody Map forgetParam){

        String account = (String)forgetParam.get("account");
        String email = (String)forgetParam.get("email");
        String emailCode = (String)forgetParam.get("emailCode");

        List<User> users = iUserService.findUserByAccount(account);
        if(users == null){
            return new RestBean(500,"此用户不存在",null);
        }else {

            if(!users.get(0).getEmail().equals(email))
                return new RestBean(500,"邮址与用户名不匹配",null);

            //邮件验证码验证过程
            String registerEmailCode = redisTemplate.opsForValue().get("verifyCode:"+email);
            if(registerEmailCode == null){
                return new RestBean(500,"验证码已过期",null);

            } else if(!registerEmailCode.equals(emailCode)){
                return new RestBean(500,"验证码错误",null);
            }

            return new RestBean(200,"下一步",null);
        }




    }


    /**
     * 重置密码
     * @param user
     * @return
     */
    @PostMapping("/resetPwd")
    public RestBean<Void> resetPwd(@RequestBody User user){

        User user1 = new User();
        user1.setAccount(user.getAccount());
        user1.setPassword(user.getPassword());

        if(iUserService.resetPassword(user1))
        return new RestBean(200,"密码修改成功",null);

        return new RestBean(500,"密码修改失败",null);
    }

    /**
     * 邮箱验证码获取
     **/
    @GetMapping(value = {"/registerVerifyCode{email}"})
    public RestBean<Void> registerVerifyCode(@PathVariable String email){
        if(emailVerify.sendVerifyCode(email))
        return new RestBean(200,"邮件发送成功",null);

        return new RestBean(500,"邮件发送失败",null);
    }

    @RequestMapping("/logout")
    public RestBean<ScenicOrder> logout(){
        return new RestBean(200,"退出登录",null);
    }
}
