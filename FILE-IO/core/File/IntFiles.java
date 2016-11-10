// Written by F.Q Member from HomieStart group http://creativecommons.org/publicdomain/zero/1.0/

package core.File;

/*****************************
 * @title Java-IO FILES      *
 * @author TR-X Homie        *
 * @version 1.0              *
 * @github HomieStart        *
 * @license Creative Commons *
 ****************************/

public interface IntFiles {
    public void FileCopyStream(String path, String path2);
    public void FileReadStream(String path);
    public void FileWriteStream(String path, String context);
}
