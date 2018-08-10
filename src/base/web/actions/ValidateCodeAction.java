package base.web.actions;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import kdf.web.action.BaseAction;

import org.apache.struts2.interceptor.SessionAware;

public class ValidateCodeAction extends BaseAction implements SessionAware {
	private InputStream imageStream;    
    private Map session;  
    
	public String getCheckCodeImage(String str, int show, ByteArrayOutputStream output) {    
        Random random = new Random();    
        BufferedImage image = new BufferedImage(80, 20, BufferedImage.TYPE_3BYTE_BGR);    
        Font font = new Font("Arial", Font.PLAIN, 18);    
        int distance = 18;    
        Graphics d = image.getGraphics();    
        d.setColor(Color.WHITE);    
        d.fillRect(0, 0, image.getWidth(), image.getHeight());    
        d.setColor(new Color(random.nextInt(100) + 100, random.nextInt(100) + 100, random.nextInt(100) + 100));    
        for (int i = 0; i < 10; i++) {    
            d.drawLine(random.nextInt(image.getWidth()), random.nextInt(image.getHeight()), random.nextInt(image.getWidth()),    
                    random.nextInt(image.getHeight()));    
        }    
        d.setColor(Color.BLACK);    
        d.setFont(font);    
        String checkCode = "";    
        char tmp;    
        int x = -distance;    
        for (int i = 0; i < show; i++) {    
            tmp = str.charAt(random.nextInt(str.length() - 1));    
            checkCode = checkCode + tmp;    
            x = x + distance;    
            d.setColor(new Color(random.nextInt(100) + 50, random.nextInt(100) + 50, random.nextInt(100) + 50));    
            d.drawString(tmp + "", x, random.nextInt(image.getHeight() - (font.getSize())) + (font.getSize()));    
        }    
        d.dispose();    
        try {    
            ImageIO.write(image, "jpg", output);    
        } catch (IOException e) {    
            e.printStackTrace();
        }    
        return checkCode;    
    }    
   
    public String execute() throws Exception {    
        ByteArrayOutputStream output = new ByteArrayOutputStream();    
        String checkCode = getCheckCodeImage("ABCDEFGHJKLMNPQRSTUVWXYZ123456789", 4, output);    
        this.session.put("validateCode", checkCode);    
        //这里将output stream转化为 inputstream    
        this.imageStream = new ByteArrayInputStream(output.toByteArray());    
        output.close();    
        return SUCCESS;    
    }    
   
    public InputStream getImageStream() {    
        return imageStream;    
    }    
   
    public void setSession(Map session) {    
        this.session = session;    
    }  


}
