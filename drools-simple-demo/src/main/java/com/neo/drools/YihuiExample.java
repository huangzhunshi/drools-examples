package com.neo.drools;

import com.neo.drools.entity.Message;
import com.neo.drools.entity.YiHuiModel;
import org.drools.core.marshalling.impl.ProtobufMessages;
import org.kie.api.KieServices;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import java.io.UnsupportedEncodingException;

public class YihuiExample {
    public static void main(String[] args){
//        KieServices kieServices = KieServices.Factory.get();
//        KieContainer kieContainer = kieServices.getKieClasspathContainer();//kmodule.xml
//        KieSession kieSession = kieContainer.newKieSession("HelloWorldKS");
//        kieSession.insert("2");
//
//        int i = kieSession.fireAllRules();//fire:火
//        System.out.println(i+"条规则");
//        kieSession.dispose();


//        KieServices kieServices = KieServices.Factory.get();
//        KieContainer kieContainer = kieServices.getKieClasspathContainer();//kmodule.xml
//        KieSession kieSession = kieContainer.newKieSession("HelloWorldKS");
//
//        YiHuiModel yiHuiModel=new YiHuiModel();
//        yiHuiModel.setId(1);
//        kieSession.insert(yiHuiModel);
//
//        int i = kieSession.fireAllRules();//fire:火
//
//
//        System.out.println(i+"条规则");
//        kieSession.dispose();

        aaaa();
    }

    /**
     *
     */
    private static void aaaa(){
        //rule,rule2可以放在数据库中，有个唯一code和他们对于，代码要执行规则的时候，根据code从数据库获取出来就OK了，这样自己开发的规则管理系统那边对数据库里的规则进行维护就行了
        String rule = "package com.neo.drools\r\n";
        rule += "import com.neo.drools.entity.Message;\r\n";
        rule += "rule \"rule1\"\r\n";
        rule += "\twhen\r\n";
        rule += "Message( status == 1, myMessage : msg )";
        rule += "\tthen\r\n";
        rule += "\t\tSystem.out.println( 1+\":\"+myMessage );\r\n";
        rule += "end\r\n";


        String rule2 = "package com.neo.drools\r\n";
        rule += "import com.neo.drools.entity.Message;\r\n";

        rule += "rule \"rule2\"\r\n";
        rule += "\twhen\r\n";
        rule += "Message( status == 2, myMessage : msg )";
        rule += "\tthen\r\n";
        rule += "\t\tSystem.out.println( 2+\":\"+myMessage );\r\n";
        rule += "end\r\n";


        StatefulKnowledgeSession kSession = null;
        try {


            KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
            //装入规则，可以装入多个
            kb.add(ResourceFactory.newByteArrayResource(rule.getBytes("utf-8")), ResourceType.DRL);
            kb.add(ResourceFactory.newByteArrayResource(rule2.getBytes("utf-8")), ResourceType.DRL);

            KnowledgeBuilderErrors errors = kb.getErrors();
            for (KnowledgeBuilderError error : errors) {
                System.out.println(error);
            }
            KnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();
            kBase.addKnowledgePackages(kb.getKnowledgePackages());

            kSession = kBase.newStatefulKnowledgeSession();


            Message message1 = new Message();
            message1.setStatus(1);
            message1.setMsg("hello world!");

            Message message2 = new Message();
            message2.setStatus(2);
            message2.setMsg("hi world!");

            kSession.insert(message1);
            kSession.insert(message2);
            kSession.fireAllRules();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            if (kSession != null)
                kSession.dispose();
        }
    }

    public void abb(){
        System.out.println("abb");
    }
}
