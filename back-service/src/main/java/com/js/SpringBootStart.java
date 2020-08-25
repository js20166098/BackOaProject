package com.js;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Description 启动类
 * @Author jishubu
 * @ComponentScan 测试开发时开启注解可以运行swagger
 */
@SpringBootApplication
@Slf4j
@EnableCaching
@EnableScheduling
public class SpringBootStart {
    public static void main(String[] args) {
        try {
            SpringApplication.run(SpringBootStart.class, args);
            log.info(print());
            log.info("项目启动成功");
        } catch (Exception e) {
            log.info("项目启动失败");
        }

    }

    private static synchronized String print() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("                   _ooOoo_\n");
        sb.append("                  o8888888o\n");
        sb.append("                  88\" . \"88\n");
        sb.append("                  (| -_- |)\n");
        sb.append("                  O\\  =  /O\n");
        sb.append("               ____/`---'\\____\n");
        sb.append("             .'  \\\\|     |//  `.\n");
        sb.append("            /  \\\\|||  :  |||//  \\ \n");
        sb.append("           /  _||||| -:- |||||-  \\ \n");
        sb.append("           |   | \\\\\\  -  /// |   |\n");
        sb.append("           | \\_|  ''\\---/''  |   |\n");
        sb.append("           \\  .-\\__  `-`  ___/-. /\n");
        sb.append("         ___`. .'  /--.--\\  `. . __\n");
        sb.append("      .\"\" '<  `.___\\_<|>_/___.'  >'\"\".\n");
        sb.append("     | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |\n");
        sb.append("     \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /\n");
        sb.append("======`-.____`-.___\\_____/___.-`____.-'======\n");
        sb.append("                   `=---='\n");
        sb.append("...................................................\n");
        sb.append("  佛祖保佑    iii    永不宕机     iii    永无BUG");
        return sb.toString();
    }
}
