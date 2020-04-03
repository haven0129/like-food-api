package com.likefood.utils;

import com.likefood.exception.LikefoodException;
import com.likefood.pojo.common.ConstantValue;
import com.likefood.pojo.common.FileUploadResult;
import com.likefood.pojo.common.MsgValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
    /**
     * 压缩图片
     * @param path
     * @return
     */
    public static String[] imagesYs(String path) {
        String[] re = new String[2];
        System.out.println("imageUploading......");
        String fullPath = path;
        fullPath = fullPath.replace("\"", "");
        fullPath = fullPath.replaceAll("\\\\", "/");
        System.out.println("fullPath = " + fullPath);
        try {
            String imgPath = fullPath.substring(0, fullPath.lastIndexOf("/") + 1);
            String imgName = fullPath.substring(fullPath.lastIndexOf("/") + 1);
            //生成压缩图
            re = ImageCompressUtils.scalingPic(imgPath, imgName);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return re;
    }

    public static String imagesYsM(String path) {
        System.out.println("imageUploading......");
        String fullPath = path;
        fullPath = fullPath.replace("\"", "");
        fullPath = fullPath.replaceAll("\\\\", "/");
        System.out.println("fullPath = " + fullPath);
        try {
            String imgPath = fullPath.substring(0, fullPath.lastIndexOf("/") + 1);
            String imgName = fullPath.substring(fullPath.lastIndexOf("/") + 1);
            //生成压缩图
            return ImageCompressUtils.scalingPicM(imgPath, imgName);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return "";
    }


    /**
     * 上传图片
     * @param file
     * @return
     * @throws IOException
     */
    public static FileUploadResult uploadImage(MultipartFile file) throws IOException, LikefoodException {
        String date = DateUtils.formatDate(new Date());
        String uuid = UUID.randomUUID().toString();
        String savePath = ConstantValue.savePath;
        String contentType = file.getContentType();
        Boolean isImage = false;
        String fileType = "jpg";
        if(StringUtils.isEmptyOrNull(contentType)){
            throw new LikefoodException(MsgValue.NOT_IMAGE);
//            return buildFileUploadResult(MsgValue.FALSE, MsgValue.NOT_IMAGE);
        }else{
            String[] strArrays = contentType.split("/");
            isImage = strArrays[0].equals("image");
            fileType = strArrays[1] == null ? "jpg" : strArrays[1];
            logger.info(fileType);
        }

        if(!(isImage)){				// 不符合文件格式要求
            logger.error(MsgValue.NOT_IMAGE);
            throw new LikefoodException(MsgValue.NOT_IMAGE);
//            return buildFileUploadResult(MsgValue.FALSE, MsgValue.NOT_IMAGE);
        }

        String imgName = uuid + "." + fileType;    // 图片名
        String dirPath = savePath  + date;        // 图片保存文件夹
        String filePath = dirPath + "/" + imgName;   // 图片完整路径
        File f = new File(dirPath);
        if(!f.exists()){
            f.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath);
        out.write(file.getBytes());
        out.flush();
        out.close();

        String url = ConstantValue.IMG_PATH + "/" + date + "/" + imgName;
        String re = imagesYsM(filePath);
        String urlm = "";
        if(!StringUtils.isEmptyOrNull(re)){
            urlm = ConstantValue.IMG_PATH + "/" + re.replace(savePath, "");
        }
       /* String[] re = imagesYs(filePath);
        String urls = "";
        if(!StringUtils.isEmptyOrNull(re[1])){
            urls = ConstantValue.IMG_PATH + "/" + re[1].replace(savePath, "");
        }
        String urlm = "";
        if(!StringUtils.isEmptyOrNull(re[0])){
            urlm = ConstantValue.IMG_PATH + "/" + re[0].replace(savePath, "");
        }*/
        return buildFileUploadResult(MsgValue.TRUE, MsgValue.SUCCESS, url, urlm);
    }

    public static FileUploadResult buildFileUploadResult(boolean success, String msg){
        FileUploadResult result = new FileUploadResult();
        result.setSuccess(success);
        result.setMessage(msg);
        return result;
    }
    public static FileUploadResult buildFileUploadResult(boolean success, String msg, String url, String urlm){
        FileUploadResult result = buildFileUploadResult(success, msg);
        result.setUrl(url);
        result.setUrlm(urlm);
//        result.setUrls(urls);
        return result;
    }
}
