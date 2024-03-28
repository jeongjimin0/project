package com.example.toy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.Map;

//import com.example.toy.Encoder;
import org.ini4j.Ini;

import com.minerva.crypto.mng.mCryptoMngApi;
import com.windfire.agents.data.asys.ext.asysExtArchivePut;

public class Encrypt extends asysExtArchivePut
{

    public static void main(String[] args) {

        Encrypt encrypt = new Encrypt();
        String filePath = "/home/pj394121/3142.jpg";

        boolean result = encrypt.putFilter(filePath, "", new StringBuffer(""), null);

        System.out.println(result);
    }

    public static String certID = null;
    public static String certPW = null;
    public static String EcmServer = null;
    public static int EcmPort = 0;
    public static String PathErr = null;
    public static String PathOut = null;

    String xvarmUSER = "SUPER";
    String xvarmPW = "SUPER";
    String xvarmDESC = "ENCRYPT";

    FileOutputStream fosErr = null;
    FileOutputStream fosOut = null;
    PrintStream psErr = null;
    PrintStream psOut = null;

    mCryptoMngApi CryptoMng = new mCryptoMngApi();
    Date today;

    @Override
    public boolean putFilter(String srcFilePath, String parms, StringBuffer err, Object pool)
    {
        String return_filename = srcFilePath;
        try
        {
            System.out.println("asdfasdfsadfasdafsdsdfadsfaasdf");
            if (!readIni("EnvCrypto.ini")) {
                return false;
            }

            this.fosErr = new FileOutputStream(PathErr, true);
            this.fosOut = new FileOutputStream(PathOut, true);

            this.psErr = new PrintStream(this.fosErr);
            this.psOut = new PrintStream(this.fosOut);

            System.setErr(this.psErr);
            System.setOut(this.psOut);
            try
            {
                int select_ret = this.CryptoMng.MvCrypto_Set(EcmServer, EcmPort, this.xvarmDESC, this.xvarmUSER, this.xvarmPW);
                System.out.println("EcmServer : " + EcmServer + ", EcmPort : " + EcmPort);
                if (select_ret != 0)
                {
                    this.today = new Date();
                    System.err.println(this.today + "E-0.암호화키 조회 실패(" + select_ret + "), 일반파일명 : " + srcFilePath + " 으로 리턴");

                    if (this.psErr != null) this.psErr.close();
                    if (this.psOut != null) this.psOut.close();
                    try
                    {
                        if (this.fosErr != null) this.fosErr.close();
                        if (this.fosOut != null) this.fosOut.close();

                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    return false;
                }

                int ret_create = this.CryptoMng.MvCrypto_Create(certID, certPW);
                if (ret_create != 0)
                {
                    this.today = new Date();
                    System.err.println(this.today + "E- 1.암호화 인스턴스 실패(" + ret_create + "), 일반파일명 : " + srcFilePath + " 으로 리턴");

                    if (this.psErr != null) this.psErr.close();
                    if (this.psOut != null) this.psOut.close();
                    try
                    {
                        if (this.fosErr != null) this.fosErr.close();
                        if (this.fosOut != null) this.fosOut.close();

                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    return false;
                }

                String encFilePath = srcFilePath + "_enc";
                int ret_encrypt = this.CryptoMng.MvCrypto_EncFile(srcFilePath, encFilePath, 0);
                if (ret_encrypt != 0)
                {
                    this.today = new Date();

                    if (!new File(srcFilePath).exists())
                    {
                        System.err.println(this.today + "E-3.암호화 실패 (" + ret_encrypt + "), 일반파일명 : " + srcFilePath + "으로 리턴");
                    }
                    else
                    {
                        System.err.println(this.today + "E-2.암호화 실패(" + ret_encrypt + "), 일반파일명 : " + srcFilePath + "으로 리턴");

                        if (this.psErr != null) this.psErr.close();
                        if (this.psOut != null) this.psOut.close();
                        try
                        {
                            if (this.fosErr != null) this.fosErr.close();
                            if (this.fosOut != null) this.fosOut.close();

                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        return false;
                    }

                }

                File f_encrypt = new File(encFilePath);
                if (!f_encrypt.exists())
                {
                    this.today = new Date();
                    System.err.println(this.today + "S-1.암호화는 성공 코드이지만 암호화 파일 존재하지 않음. (" + encFilePath + ")");

                    if (this.psErr != null) this.psErr.close();
                    if (this.psOut != null) this.psOut.close();
                    try
                    {
                        if (this.fosErr != null) this.fosErr.close();
                        if (this.fosOut != null) this.fosOut.close();

                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    return false;
                }

                File f_normal = new File(srcFilePath);
                if (!f_normal.exists())
                {
                    this.today = new Date();
                    return_filename = encFilePath;
                    System.err.println(this.today + "S-2.암호화는 성공 코드이지만 일반파일이 존재하지 않음(" + srcFilePath + ")");

                    if (this.psErr != null) this.psErr.close();
                    if (this.psOut != null) this.psOut.close();
                    try
                    {
                        if (this.fosErr != null) this.fosErr.close();
                        if (this.fosOut != null) this.fosOut.close();

                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    return false;
                }

                this.today = new Date();
                if (!f_normal.delete())
                {
                    return_filename = encFilePath;
                    System.err.println(this.today + "S-3.암호화는 성공 코드이지만 일반파일삭제실패(" + f_normal.getName() + ")로 암호된파일 명을 반환(" + f_encrypt.getName() + ")");

                    if (this.psErr != null) this.psErr.close();
                    if (this.psOut != null) this.psOut.close();
                    try
                    {
                        if (this.fosErr != null) this.fosErr.close();
                        if (this.fosOut != null) this.fosOut.close();

                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    return false;
                }

                if (f_encrypt.renameTo(f_normal))
                {
                    return_filename = srcFilePath;

                    if (this.psErr != null) this.psErr.close();
                    if (this.psOut != null) this.psOut.close();
                    try
                    {
                        if (this.fosErr != null) this.fosErr.close();
                        if (this.fosOut != null) this.fosOut.close();

                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    return true;
                }

                return_filename = encFilePath;
                System.err.println(this.today + "S-4.암호화는 성공 코드이지만 암호화된파일을 일반파일명 변경 실패로 암호화된파일 명을 반환(" + f_encrypt.getName() + ")");

                if (this.psErr != null) this.psErr.close();
                if (this.psOut != null) this.psOut.close();
                try
                {
                    if (this.fosErr != null) this.fosErr.close();
                    if (this.fosOut != null) this.fosOut.close();

                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                return false;
            }
            catch (Exception e)
            {
                return_filename = srcFilePath;
                e.printStackTrace();
                System.out.println(e.getMessage());

                if (this.psErr != null) this.psErr.close();
                if (this.psOut != null) this.psOut.close();
                try
                {
                    if (this.fosErr != null) this.fosErr.close();
                    if (this.fosOut != null) this.fosOut.close();

                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
                return false;
            }

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
        finally
        {
            if (this.psErr != null) this.psErr.close();
            if (this.psOut != null) this.psOut.close();
            try
            {
                if (this.fosErr != null) this.fosErr.close();
                if (this.fosOut != null) this.fosOut.close();

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        //throw localObject;
    }

    public boolean readIni(String path)
    {
        if (EcmServer != null) return true;

        Ini ini = new Ini();

        Map map = null;
        try
        {
            File file = new File(path);

            if (!file.exists())
            {
                this.fosErr = new FileOutputStream("/home/p344013/xtorm/log/Env_error.log", true);
                this.psErr = new PrintStream(this.fosErr);
                System.setErr(this.psErr);
                System.err.println("ini not found:" + path);
                return false;
            }
            ini.load(new FileReader(file));

            map = (Map)ini.get("EnvCrypto");

            EcmServer = (String)map.get("EcmServer");
            EcmPort = Integer.parseInt((String)map.get("EcmPort"));
            certID = (String)map.get("certID");
            certPW = (String)map.get("certPW");
            PathErr = (String)map.get("PathErr_En");
            PathOut = (String)map.get("PathOut_En");

            map.clear();
            return true;
        }
        catch (Exception e) {
            try {
                this.fosErr = new FileOutputStream("/home/p344013/xtorm/log/Env_error.log", true);
                this.psErr = new PrintStream(this.fosErr);
                System.setErr(this.psErr);
                System.setOut(this.psErr);
            }
            catch (FileNotFoundException e1)
            {
                e1.printStackTrace();
            }
            e.printStackTrace();
            System.err.println(e.getMessage());
        }return false;
    }



}
