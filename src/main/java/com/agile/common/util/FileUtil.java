package com.agile.common.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by 佟盟 on 2017/12/21
 */
public class FileUtil extends FileUtils {
    private final static Map<String, Object> FILE_FORMAT_MAP = new HashMap<>();
    static
    {
        FILE_FORMAT_MAP.put("FFD8FFE", "JPG");
        FILE_FORMAT_MAP.put("89504E47", "PNG");
        FILE_FORMAT_MAP.put("47494638", "GIF");
        FILE_FORMAT_MAP.put("49492A00", "TIF");
        FILE_FORMAT_MAP.put("424D", "BMP");
        FILE_FORMAT_MAP.put("41433130", "DWG");
        FILE_FORMAT_MAP.put("68746D", "HTML");
        FILE_FORMAT_MAP.put("0D0A3C", "HTM");
        FILE_FORMAT_MAP.put("48544D4C207B0D0A0942", "CSS");
        FILE_FORMAT_MAP.put("696B2E71623D696B2E71", "JS");
        FILE_FORMAT_MAP.put("7B5C727466", "RTF");
        FILE_FORMAT_MAP.put("38425053", "PSD");
        FILE_FORMAT_MAP.put("44656C69766572792D646174653A", "EML");
        FILE_FORMAT_MAP.put("D0CF11E0", new String[]{"DOC","XLS"});
        FILE_FORMAT_MAP.put("5374616E64617264204A", "MDB");
        FILE_FORMAT_MAP.put("252150532D41646F6265", "PS");
        FILE_FORMAT_MAP.put("255044462D312E", "PDF");
        FILE_FORMAT_MAP.put("2E524D46", "RMVB");
        FILE_FORMAT_MAP.put("464C5601050000000900", "FLV");
        FILE_FORMAT_MAP.put("00000020667479706D70", "MP4");
        FILE_FORMAT_MAP.put("49443303000000002176", "MP3");
        FILE_FORMAT_MAP.put("000001BA", "MPG");
        FILE_FORMAT_MAP.put("000001B3", "MPG");
        FILE_FORMAT_MAP.put("3026B2758E66CF11", "WMV");
        FILE_FORMAT_MAP.put("57415645", "WAV");
        FILE_FORMAT_MAP.put("41564920", "AVI");
        FILE_FORMAT_MAP.put("4D546864", "MID");
        FILE_FORMAT_MAP.put("504B0304", "ZIP");
        FILE_FORMAT_MAP.put("52617221", "RAR");
        FILE_FORMAT_MAP.put("235468697320636F6E66", "INI");
        FILE_FORMAT_MAP.put("504B03040A0000000000", "JAR");
        FILE_FORMAT_MAP.put("4D5A9000030000000400", "EXE");
        FILE_FORMAT_MAP.put("3C25402070616765206C", "JSP");
        FILE_FORMAT_MAP.put("4D616E69666573742D56", "MF");
        FILE_FORMAT_MAP.put("3C3F786D6C", "XML");
        FILE_FORMAT_MAP.put("494E5345525420494E54", "SQL");
        FILE_FORMAT_MAP.put("7061636B61", "JAVA");
        FILE_FORMAT_MAP.put("0D0A726F", "BAT");
        FILE_FORMAT_MAP.put("1F8B0800000000000000", "GZ");
        FILE_FORMAT_MAP.put("6C6F67346A2E726F6F74", "PROPERTIES");
        FILE_FORMAT_MAP.put("CAFEBABE", "CLASS");
        FILE_FORMAT_MAP.put("49545346030000006000", "CHM");
        FILE_FORMAT_MAP.put("04000000010000001300", "MXP");
        FILE_FORMAT_MAP.put("504B0304140006000800", "DOCX");
        FILE_FORMAT_MAP.put("D0CF11E0A1B11AE10000",  new String[]{"WPS","VSD"});
        FILE_FORMAT_MAP.put("6431303A637265617465", "TORRENT");
        FILE_FORMAT_MAP.put("3C68746D6C20786D6C6E", "HTM");
        FILE_FORMAT_MAP.put("46726F6D3A3CD3C920CD", "MHT");
        FILE_FORMAT_MAP.put("6D6F6F76", "MOV");
        FILE_FORMAT_MAP.put("FF575043", "WPD");
        FILE_FORMAT_MAP.put("CFAD12FEC5FD746F", "DBX");
        FILE_FORMAT_MAP.put("2142444E", "PST");
        FILE_FORMAT_MAP.put("AC9EBD8F", "QDF");
        FILE_FORMAT_MAP.put("E3828596", "PWL");
        FILE_FORMAT_MAP.put("2E7261FD", "RAM");
    }

    /**
     * 获取文件格式
     */
    public static String getFormat(File file){
        try {
            FileInputStream in = new FileInputStream(file);
            byte[] header = new byte[20];
            in.read(header);
            String headerString = Objects.requireNonNull(StringUtil.coverToHex(header));
            for(Map.Entry<String,Object> map:FILE_FORMAT_MAP.entrySet()){
                if(headerString.contains(map.getKey())){
                    Object value = map.getValue();
                    String suffix = ArrayUtil.getLast(file.getName().split(".", -1)).toString().toUpperCase();
                    if(ObjectUtil.isEmpty(value)){
                        return suffix;
                    }
                    if(value.getClass().isArray()){
                        String[] values = (String[]) value;

                        if(ArrayUtil.indexOf(values,suffix)>-1)return suffix.toUpperCase();
                        return null;
                    }
                    return map.getValue().toString();
                }
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static class RETURN{
        private String fileName;
        private long fileSize;
        private String contentType;
        private String state;
        private String msg;

        public RETURN(String fileName, long fileSize, String contentType) {
            this.fileName = fileName;
            this.fileSize = fileSize;
            this.contentType = contentType;
        }

        public void setInfo(com.agile.common.base.RETURN re){
            this.setMsg(re.getMsg());
            this.setState(re.getCode());
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public long getFileSize() {
            return fileSize;
        }

        public void setFileSize(long fileSize) {
            this.fileSize = fileSize;
        }

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
