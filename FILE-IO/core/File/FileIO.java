// Written by F.Q Member from HomieStart group http://creativecommons.org/publicdomain/zero/1.0/

package core.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/*****************************
 * @title Java-IO FILES      *
 * @author TR-X Homie        *
 * @version 1.0              *
 * @github HomieStart        *
 * @license Creative Commons *
 ****************************/

public final class FileIO extends Files implements IntFiles {

    public FileIO(int KiloBytes) {
        super(KiloBytes);
    }
    
    @Override
    public void FileReadStream(String path){
        long inTimer;
        inTimer = System.currentTimeMillis();
        try(InputStream in = new FileInputStream(path)){
            byte[] bts = new byte[BUFFER_SIZE];
            int i = in.read(bts, 0, bts.length);
            while(i != -1){
                System.out.println((char) i + " - " + i);
                i = in.read();
            }
        }catch(Exception e){
            System.out.println("Error:"+e);
        }
        this.TIMER = (inTimer - System.currentTimeMillis());
        //System.out.println("Time:" + (inTimer - System.currentTimeMillis()));
    }
    
    
    @Override
    public void FileWriteStream(String path, String context){
        long inTimer;
        inTimer = System.currentTimeMillis();
        try(OutputStream out = new FileOutputStream(path)){
            byte[] bts = new byte[BUFFER_SIZE];
            out.write(context.getBytes()); 
        }catch(Exception e){
            System.out.println("Error:"+e);
        }
        this.TIMER = (inTimer - System.currentTimeMillis());
        //System.out.println("Time:" + (inTimer - System.currentTimeMillis()));
    }
    
    @Override
    public void FileCopyStream(String path, String path2){
        long inTimer;
        inTimer = System.currentTimeMillis();
        try(OutputStream out = new FileOutputStream(path)){
            try(InputStream file = new FileInputStream(path2)){
                 byte[] bts = new byte[BUFFER_SIZE];
            int byteread;
            while((byteread = file.read(bts)) != -1){
                out.write(bts, 0, byteread);
             }
            }
        }catch(Exception e){
            System.out.println("Error:"+e);
        }
        this.TIMER = (inTimer - System.currentTimeMillis());
        //System.out.println("Time:" + (inTimer - System.currentTimeMillis()));
    }
}
