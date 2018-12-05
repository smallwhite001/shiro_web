package factory;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {
    public LinkedHashMap<String,String> buildFilterChainDefinitionMap(){
      LinkedHashMap<String ,String> map = new LinkedHashMap<String,String>();
       //可以从数据库中获取相应信息
      map.put("/login.jsp","anon");
        map.put("/shiro/login","anon");
        map.put("/shiro/logout","logout");
        //remember me可以访问
        map.put("/index.jsp","user");
        //remember不可以直接访问
        map.put("/user.jsp","authc,roles[user]");
        map.put("/admin.jsp","authc,roles[admin]");
        map.put("/**","authc");
      return map;
    }
}
