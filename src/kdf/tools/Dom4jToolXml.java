package kdf.tools;


import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;



public class Dom4jToolXml 
{
	
	
	/** 锟斤拷锟斤拷锟絏ML锟斤拷式锟斤拷String 转锟斤拷为XML Document
	    * string2Document 
	    * 锟斤拷锟街凤拷转为Document 
	    * @return 
	    * @param s xml锟斤拷式锟斤拷锟街凤拷 
	    */ 
	   public static Document string2Document(String s) 
	   { 
	      Document doc = null; 
	      try 
	      { 
	           doc = DocumentHelper.parseText(s); 
	      }catch(Exception ex) 
	      {             
	           ex.printStackTrace(); 
	      } 
	      return doc; 
	   }
	   
	  

	   /** 
	    *  锟斤拷Document锟斤拷锟襟保达拷为一锟斤拷xml锟侥硷拷锟斤拷锟斤拷锟斤拷
	    * doc2XmlFile 
	    * 锟斤拷Document锟斤拷锟襟保达拷为一锟斤拷xml锟侥硷拷锟斤拷锟斤拷锟斤拷 
	    * @return true:锟斤拷锟斤拷晒锟� flase:失锟斤拷 
	    * @param filename 锟斤拷锟斤拷锟斤拷募锟斤拷锟� 
	    * @param document 锟斤拷要锟斤拷锟斤拷锟絛ocument锟斤拷锟斤拷 
	    */ 
	   public static boolean doc2XmlFile(Document document,String filename) 
	   { 
	      boolean flag = true; 
	      try 
	      { 
	            /* 锟斤拷document锟叫碉拷锟斤拷锟斤拷写锟斤拷锟侥硷拷锟斤拷 */ 
	            //默锟斤拷为UTF-8锟斤拷式锟斤拷指锟斤拷为"GB2312" 
	            OutputFormat format = OutputFormat.createPrettyPrint(); 
	        //    format.setEncoding("GB2312"); 
	            org.dom4j.io.XMLWriter writer = new org.dom4j.io.XMLWriter( new FileOutputStream(filename)); 

	          //  XMLWriter writer = new XMLWriter(new FileWriter(new File(filename)),format); 
	            writer.write(document); 
	            writer.close();             
	        }catch(Exception ex) 
	        { 
	            flag = false; 
	            ex.printStackTrace(); 
	        } 
	        return flag;       
	      }


	  

	   /** 
	    *
	    * string2XmlFile 
	    * 锟斤拷xml锟斤拷式锟斤拷锟街凤拷锟斤拷为锟斤拷锟斤拷锟侥硷拷锟斤拷锟斤拷锟斤拷址锟斤拷式锟斤拷锟斤拷锟絰ml锟斤拷锟斤拷锟津返伙拷失锟斤拷 
	    * @return true:锟斤拷锟斤拷晒锟� flase:失锟斤拷 
	    * @param filename 锟斤拷锟斤拷锟斤拷募锟斤拷锟� 
	    * @param str 锟斤拷要锟斤拷锟斤拷锟斤拷址锟� 
	    */ 
	   public static boolean string2XmlFile(String str,String filename) 
	   { 
	      boolean flag = true; 
	      try 
	      { 
	         Document doc = DocumentHelper.parseText(str);        
	         //doc.setEntityResolver(new MyEntityResolver());
	         flag = doc2XmlFile(doc,filename); 
	      }catch (Exception ex) 
	      { 
	         flag = false; 
	        ex.printStackTrace(); 
	      } 
	      return flag; 
	   }


	   
	   
	   
	   /** 
	    * xmlWriteDemoByString 
	    * 锟斤拷示String锟斤拷锟斤拷为xml锟侥硷拷 
	    */ 
	   public static void xmlWriteDemoByString() 
	   { 
	      String s = ""; 
	      /** xml锟斤拷式锟斤拷锟斤拷 "<?xml version='1.0' encoding='GB2312'?>" 锟斤拷锟皆诧拷锟斤拷写*/ 
	      s = "<config>\r\n" 
	         +"   <ftp name='DongDian'>\r\n" 
	         +"     <ftp-host>127.0.0.1</ftp-host>\r\n" 
	       +"     <ftp-port>21</ftp-port>\r\n" 
	         +"     <ftp-user>cxl</ftp-user>\r\n" 
	         +"     <ftp-pwd>longshine</ftp-pwd>\r\n" 
	         +"     <!-- ftp锟斤拷喑拷锟絣锟接达拷锟斤拷 -->\r\n" 
	         +"     <ftp-try>50</ftp-try>\r\n" 
	        +"     <!-- ftp锟斤拷锟斤拷l锟斤拷锟接筹拷时锟斤拷 -->\r\n" 
	         +"     <ftp-delay>10</ftp-delay>\r\n" 
	         +" </ftp>\r\n" 
	         +"</config>\r\n"; 
	      //锟斤拷锟侥硷拷锟斤拷傻锟絚lasses锟侥硷拷锟斤拷锟斤拷锟节碉拷目录锟斤拷    
	      
	      String  hh ="<?xml version='1.0' encoding='UTF-8'?>"
	    	        + "<!DOCTYPE user SYSTEM 'userInfo.dtd'><user><param name='category' value='01'/><param name='createdate' value='2009-09-30 14:49'/></user>";
	      		
	      
	      StringBuffer bf = new StringBuffer();
	      bf.append(hh);
	      bf.delete(1, 76);
	      hh=bf.toString();
	      string2XmlFile(hh,"C:\\xmlWriteDemoByString.xml");    
	      //锟斤拷锟侥硷拷锟斤拷傻锟絚lasses锟侥硷拷锟斤拷锟斤拷    
	     // string2XmlFile(s,"regsys/WebRoot/WEB-INF/classes/xmlWriteDemoByString.xml");   
	   }
	   
	   public static UserInfoXmlPo readXML(String pathFileName)
	   {
		   List oo=new ArrayList();
		   UserInfoXmlPo userInfo = new UserInfoXmlPo();
		    SAXReader reader = new SAXReader();
			reader.setEntityResolver(new MyEntityResolver());   //锟斤拷锟斤拷证dtd锟侥硷拷
		        try 
		        {
		            Document doc = reader.read("file:"+pathFileName);
		            List items = doc.selectNodes("/user/param");
		            Iterator it = items.iterator();
		            while (it.hasNext()) 
		            {
		                Element el = (Element) it.next();

		                if ((!(el.attributeValue("name") == null) || !el.attributeValue(
		                        "name").equalsIgnoreCase(""))) 
		                {

		                }
       
		                
		                if("userid".equals(el.attributeValue("name")))
		                {
		                	userInfo.setUserid(el.attribute("value").getValue());
		                }
		                
		                if("orgid".equals(el.attributeValue("name")))
		                {
		                	userInfo.setOrgid(el.attribute("value").getValue());
		                }
		                if("realname".equals(el.attributeValue("name")))
		                {
		                	userInfo.setRealname(el.attribute("value").getValue());
		                }
		                if("username".equals(el.attributeValue("name")))
		                {
		                	userInfo.setUsername(el.attribute("value").getValue());
		                }
		                if("email".equals(el.attributeValue("name")))
		                {
		                	userInfo.setEmail(el.attribute("value").getValue());
		                }
		                Attribute name = el.attribute("name");
		            //   System.out.println("name:---->" + name.getValue() + " ");
		                Attribute value = el.attribute("value");
		           //     System.out.println("value:--->" + value.getValue());
		            }
		            
		          
		        } catch (DocumentException e) 
		        {
		            e.printStackTrace();
		        }
		   
		   return userInfo;
	   }
	   

	 public static void main(String arge[]) 
	 {
//		  long lasting = System.currentTimeMillis();
//		  try {
//		 //  File f = new File("C:\\22222.xml");
//		   FileReader f= new FileReader(new File("C:\\22222.xml"));
//
//		   SAXReader reader = new SAXReader();
//		   Document doc = reader.read(f);
//		   
//		   Element root = doc.getRootElement();
//		   Element foo;
//		   for (Iterator i = root.elementIterator("user"); i.hasNext();) {
//		    foo = (Element) i.next();
//		    System.out.print("锟矫伙拷ID:" + foo.elementText("userid"));
//		    System.out.println("锟矫伙拷锟斤拷锟斤拷:" + foo.elementText("realname"));
//		   }
//		  } catch (Exception e)
//		  {
//		   e.printStackTrace();
//		  }
		 
		 
		 SAXReader reader = new SAXReader();
		 reader.setEntityResolver(new MyEntityResolver());   //锟斤拷锟斤拷证dtd锟侥硷拷
	        try 
	        {
	        	
	            Document doc = reader.read("E:\\jakarta-tomcat-5.0.27\\webapps\\regsys\\20091111144738_userInfo.xml");
	           
	            List items = doc.selectNodes("/user/param");
	            Iterator it = items.iterator();

	            while (it.hasNext()) 
	            {
	                Element el = (Element) it.next();

	                if ((!(el.attributeValue("name") == null) || !el.attributeValue(
	                        "name").equalsIgnoreCase(""))) 
	                {

	                }

	                Attribute name = el.attribute("name");
	                System.out.println("name:---->" + name.getValue() + " ");
	                Attribute value = el.attribute("value");
	                System.out.println("value:--->" + value.getValue());
	            }

	        } catch (DocumentException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	      //  Dom4jReaderXml.xmlWriteDemoByString();
	        
	       // readXML("E:\\jakarta-tomcat-5.0.27\\webapps\\regsys\\20091111144738_userInfo.xml");
	
	 }

		

}
