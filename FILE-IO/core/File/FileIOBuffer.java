// Written by F.Q Member from HomieStart group http://creativecommons.org/publicdomain/zero/1.0/

package core.File;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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

public final class FileIOBuffer extends Files implements IntFiles{
    /**
     * [Construct]
     * @param KiloBytes Define size of the buffer 
     */
    public FileIOBuffer(int KiloBytes) {
        super(KiloBytes);
    }
 
     /**
     * [Use for create a copy of you file with buffer]
     * @param path Path of the Orginal file
     * @param path2 path of the Copy <p>Set Location<p>
     */
    @Override
    public void FileCopyStream(String path, String path2) {
        long inTimer;
        inTimer = System.currentTimeMillis();
        try(OutputStream out = new BufferedOutputStream(new FileOutputStream(path),BUFFER_SIZE)){
            try(InputStream file = new FileInputStream(path2)){
                 byte[] bts = new byte[BUFFER_SIZE];
            int byteread;
            while((byteread = file.read(bts)) != -1){
                out.write(byteread);
             }
            }
        }catch(Exception e){
            System.out.println("Error:"+e);
        }
        this.TIMER = (inTimer - System.currentTimeMillis());
    }

    /**
     * [Use for Read a File with buffer]
     * @param path Define the path of you File
     */    
    @Override
    public void FileReadStream(String path) {
        long inTimer;
        inTimer = System.currentTimeMillis();
        try(InputStream in = new BufferedInputStream(new FileInputStream(path), this.BUFFER_SIZE)){
            int i = in.read();
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
  
    /**
     * [Use for write a File with buffer]
     * @param path Define the path of you File
     * @param context The context for write in you file
     */
    @Override
    public void FileWriteStream(String path, String context) {
        long inTimer;
        inTimer = System.currentTimeMillis();
        try(OutputStream out = new BufferedOutputStream(new FileOutputStream(path),BUFFER_SIZE)){
            out.write(context.getBytes()); 
        }catch(Exception e){
            System.out.println("Error:"+e);
        }
        this.TIMER = (inTimer - System.currentTimeMillis());
        //System.out.println("Time:" + (inTimer - System.currentTimeMillis()));
    }
    
}
