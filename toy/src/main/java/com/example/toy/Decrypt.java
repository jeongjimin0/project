package com.example.toy;

//import com.example.toy.Decoder;
import com.minerva.crypto.mng.mCryptoMngApi;
import com.windfire.agents.data.asys.ext.asysExtArchiveGet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Decrypt extends asysExtArchiveGet
{
    private static FileOutputStream fosErr = null;
    private static FileOutputStream fosOut = null;
    private static PrintStream psErr = null;
    private static PrintStream psOut = null;

    private static String certID = "xtormhant";
    private static String certPW = "hant#123";
    private static String xvarmIP = "192.168.100.35";
    private static int xvarmPORT = 20016;
    private static String xvarmUSER = "SUPER";
    private static String xvarmPW = "SUPER";
    private static String xvarmDESC = "DECRYPT";

    public String retrieveFilter(String srcFilePath, String parms, StringBuffer err) {
        int select_ret = -1;

        String decFilePath = "";
        String elementID = "";

        File SrcFile = null;
        File DecFile = null;

        mCryptoMngApi CryptoMng = new mCryptoMngApi();
        try
        {
            fosErr = new FileOutputStream("/home/p344013/xtorm/log/decrypt_error.log", true);
            fosOut = new FileOutputStream("/home/p344013/xtorm/log/decrypt_out.log", true);
            psErr = new PrintStream(fosErr);
            psOut = new PrintStream(fosOut);

            System.setErr(psErr);
            System.setOut(psOut);
            try
            {
                select_ret = CryptoMng.MvCrypto_Create(certID, certPW);
                if (select_ret != 0)
                {
                    System.err.println("MvCrypto_Create Failed(ErrorCode : " + select_ret + ")");
                    return srcFilePath;
                }

                select_ret = CryptoMng.MvCrypto_Set(xvarmIP, xvarmPORT, xvarmDESC, xvarmUSER, xvarmPW);

                if (select_ret != 0)
                {
                    System.err.println("CryptoMng_Set Failed(ErrorCode : " + select_ret + ")");
                    return srcFilePath;
                }

                SrcFile = new File(srcFilePath);
                if (SrcFile.exists()) {
                    elementID = SrcFile.getName();
                }
                else {
                    System.err.println("Get srcFilePath Failed(path: " + srcFilePath + ")");
                    return srcFilePath;
                }

                decFilePath = "/home/p344013/xtorm/Temp/" + elementID + "_dec";

                DecFile = new File(decFilePath);
                if (DecFile.exists())
                {
                    DecFile.delete();
                }

                select_ret = CryptoMng.MvCrypto_DecFile(srcFilePath, decFilePath);
                if (select_ret != 0)
                {
                    System.err.println("MvCrypto_DecFile Failed(ErrorCode : " + select_ret + ")");
                    return srcFilePath;
                }

                select_ret = CryptoMng.MvCrypto_Close();
                if (select_ret != 0)
                {
                    System.err.println("MvCrypto_Close Failed(ErrorCode : " + select_ret + ")");
                    return srcFilePath;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return decFilePath;
    }

    public static void main(String[] args) {
        Decrypt decoder = new Decrypt();

        String filePath = "C:/test/img1.jpg";

        String result = decoder.retrieveFilter(filePath, "", new StringBuffer(""));

        System.out.println(result);
    }
}
