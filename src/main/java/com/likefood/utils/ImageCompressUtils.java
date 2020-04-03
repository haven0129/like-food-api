package com.likefood.utils;

import com.sun.imageio.plugins.jpeg.JPEGImageWriter;
import org.w3c.dom.Element;

import javax.imageio.*;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.*;


public class ImageCompressUtils {
   // public static final Logger logger = Logger.getLogger(ImageCompressUtils.class);

    /**
     * 图片压缩测试
     *
     * @param args
     */
    public static void main(String args[]) {
        //读文件夹所有文件进行压缩处理
//        String f = "/var/upload_bak/";
//        File file = new File(f);
//
//        if(file.exists())
//        {
//            File[] filelist = file.listFiles();
//            for(int i = 0;i<filelist.length;i++)
//            {
//                String n = filelist[i].getName();
//                Tosmallerpic(f,filelist[i],"_middle",n,185,160,(float)0.7);
//                Tosmallerpic(f,filelist[i],"_small",n,45,45,(float)0.7);
//                Tosmallerpic(f,filelist[i],"_smaller",n,116,100,(float)0.7);
//            }
//        }
        //对一个文件进行压缩处理
        String url = "/var/upload_bak/";
        String name = "31273618df16f1ab32e76c06c2f35852.jpg";
        img_change(url, name);
    }


    public static void img_change(String url, String name) {
        Tosmallerpic(url, new File(url + name), "_middle", name, 188, 165, (float) 0.7);
        Tosmallerpic(url, new File(url + name), "_small", name, 45, 45, (float) 0.7);
        Tosmallerpic(url, new File(url + name), "_smaller", name, 116, 100, (float) 0.7);
//        Tosmallerpic(url, new File(url + name), "wwww", name, 200, 120, (float) 0.7);
    }

    /**
     * @param f    图片所在的文件夹路径
     * @param file 图片路径
     * @param ext  扩展名
     * @param n    图片名
     * @param w    目标宽
     * @param h    目标高
     * @param per  百分比
     */
    private static void Tosmallerpic(String f, File file, String ext, String n, int w, int h, float per) {
        Image src;
        try {
            src = ImageIO.read(file); //构造Image对象

            String img_midname = f + n.substring(0, n.indexOf(".")) + ext + n.substring(n.indexOf("."));
            int old_w = src.getWidth(null); //得到源图宽
            int old_h = src.getHeight(null);
            int new_w = 0;
            int new_h = 0; //得到源图长

            double w2 = (old_w * 1.00) / (w * 1.00);
            double h2 = (old_h * 1.00) / (h * 1.00);

            //图片跟据长宽留白，成一个正方形图。
            BufferedImage oldpic;
            if (old_w > old_h) {
                oldpic = new BufferedImage(old_w, old_w, BufferedImage.TYPE_INT_RGB);
            } else {
                if (old_w < old_h) {
                    oldpic = new BufferedImage(old_h, old_h, BufferedImage.TYPE_INT_RGB);
                } else {
                    oldpic = new BufferedImage(old_w, old_h, BufferedImage.TYPE_INT_RGB);
                }
            }
            Graphics2D g = oldpic.createGraphics();
            g.setColor(Color.white);
            if (old_w > old_h) {
                g.fillRect(0, 0, old_w, old_w);
                g.drawImage(src, 0, (old_w - old_h) / 2, old_w, old_h, Color.white, null);
            } else {
                if (old_w < old_h) {
                    g.fillRect(0, 0, old_h, old_h);
                    g.drawImage(src, (old_h - old_w) / 2, 0, old_w, old_h, Color.white, null);
                } else {
                    //g.fillRect(0,0,old_h,old_h);
                    g.drawImage(src.getScaledInstance(old_w, old_h, Image.SCALE_SMOOTH), 0, 0, null);
                }
            }
            g.dispose();
            src = oldpic;
            //图片调整为方形结束
            if (old_w > w)
                new_w = (int) Math.round(old_w / w2);
            else
                new_w = old_w;
            if (old_h > h)
                new_h = (int) Math.round(old_h / h2);//计算新图长宽
            else
                new_h = old_h;
            BufferedImage image_to_save = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
            image_to_save.getGraphics().drawImage(src.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0, 0, null);
            FileOutputStream fos = new FileOutputStream(img_midname); //输出到文件流

            //新的方法
            saveAsJPEG(100, image_to_save, per, fos);
            fos.close();
        } catch (IOException ex) {

        }
    }


    /**
     * 以JPEG编码保存图片
     *
     * @param dpi             分辨率
     * @param image_to_save   要处理的图像图片
     * @param JPEGcompression 压缩比
     * @param fos             文件输出流
     * @throws IOException
     */
    public static void saveAsJPEG(Integer dpi, BufferedImage image_to_save, float JPEGcompression, FileOutputStream fos) throws IOException {

        //useful documentation at http://docs.oracle.com/javase/7/docs/api/javax/imageio/metadata/doc-files/jpeg_metadata.html
        //useful example program at http://johnbokma.com/java/obtaining-image-metadata.html to output JPEG data

        //old jpeg class
        //com.sun.image.codec.jpeg.JPEGImageEncoder jpegEncoder  =  com.sun.image.codec.jpeg.JPEGCodec.createJPEGEncoder(fos);
        //com.sun.image.codec.jpeg.JPEGEncodeParam jpegEncodeParam  =  jpegEncoder.getDefaultJPEGEncodeParam(image_to_save);

        // Image writer
        JPEGImageWriter imageWriter = (JPEGImageWriter) ImageIO.getImageWritersBySuffix("jpg").next();
        ImageOutputStream ios = ImageIO.createImageOutputStream(fos);
        imageWriter.setOutput(ios);
        //and metadata
        IIOMetadata imageMetaData = imageWriter.getDefaultImageMetadata(new ImageTypeSpecifier(image_to_save), null);


        if (dpi != null && !dpi.equals("")) {

            //old metadata
            //jpegEncodeParam.setDensityUnit(com.sun.image.codec.jpeg.JPEGEncodeParam.DENSITY_UNIT_DOTS_INCH);
            //jpegEncodeParam.setXDensity(dpi);
            //jpegEncodeParam.setYDensity(dpi);

            //new metadata
            Element tree = (Element) imageMetaData.getAsTree("javax_imageio_jpeg_image_1.0");
            Element jfif = (Element) tree.getElementsByTagName("app0JFIF").item(0);
            jfif.setAttribute("Xdensity", Integer.toString(dpi));
            jfif.setAttribute("Ydensity", Integer.toString(dpi));

        }


        if (JPEGcompression >= 0 && JPEGcompression <= 1f) {

            //old compression
            //jpegEncodeParam.setQuality(JPEGcompression,false);

            // new Compression
            JPEGImageWriteParam jpegParams = (JPEGImageWriteParam) imageWriter.getDefaultWriteParam();
            jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
            jpegParams.setCompressionQuality(JPEGcompression);

        }

        //old write and clean
        //jpegEncoder.encode(image_to_save, jpegEncodeParam);

        //new Write and clean up
        imageWriter.write(imageMetaData, new IIOImage(image_to_save, null, null), null);
        ios.close();
        imageWriter.dispose();

    }


    public static boolean compressPic(String srcFilePath, String descFilePath) {
        File file = null;
        BufferedImage src = null;
        FileOutputStream out = null;
        ImageWriter imgWrier;
        ImageWriteParam imgWriteParams;

        // 指定写图片的方式为 jpg
        imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
        imgWriteParams = new JPEGImageWriteParam(null);
        // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
        imgWriteParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        // 这里指定压缩的程度，参数qality是取值0~1范围内，
        imgWriteParams.setCompressionQuality((float) 0.1);
        imgWriteParams.setProgressiveMode(ImageWriteParam.MODE_DISABLED);
        ColorModel colorModel = ColorModel.getRGBdefault();
        // 指定压缩时使用的色彩模式
        imgWriteParams.setDestinationType(new ImageTypeSpecifier(colorModel, colorModel
                .createCompatibleSampleModel(16, 16)));

        try {
            if (srcFilePath == null || srcFilePath.isEmpty()) {
                return false;
            } else {
                file = new File(srcFilePath);
                src = ImageIO.read(file);
                out = new FileOutputStream(descFilePath);

                imgWrier.reset();
                // 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何 OutputStream构造
                imgWrier.setOutput(ImageIO.createImageOutputStream(out));
                // 调用write方法，就可以向输入流写图片
                imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static byte[] compressPic(byte[] data) {
        byte[] compressedData = null;
        ByteArrayInputStream is = new ByteArrayInputStream(data);
        BufferedImage src = null;
        ByteArrayOutputStream out = null;
        try {

            ImageWriter imgWrier;
            ImageWriteParam imgWriteParams;

            // 指定写图片的方式为 jpg
            imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
            imgWriteParams = new JPEGImageWriteParam(null);
            // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
            imgWriteParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            // 这里指定压缩的程度，参数qality是取值0~1范围内，
            imgWriteParams.setCompressionQuality((float) 0.5);

            imgWriteParams.setProgressiveMode(ImageWriteParam.MODE_DISABLED);
            ColorModel colorModel = ColorModel.getRGBdefault();
            // 指定压缩时使用的色彩模式
            imgWriteParams.setDestinationType(new ImageTypeSpecifier(colorModel, colorModel
                    .createCompatibleSampleModel(16, 16)));

            src = ImageIO.read(is);
            out = new ByteArrayOutputStream(data.length);

            imgWrier.reset();
            // 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何 OutputStream构造
            imgWrier.setOutput(ImageIO.createImageOutputStream(out));
            // 调用write方法，就可以向输入流写图片
            imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);
            compressedData = out.toByteArray();

            src.flush();
            out.flush();
            out.close();
            is.close();

        } catch (Exception e) {
            e.printStackTrace();
            //logger.error(e.getMessage());
        }finally {

        }
        return compressedData;
    }



    /**
     * 将图片按指定宽度缩放
     * @param path    图片所在的文件夹路径
     * @param file 图片路径
     * @param ext  扩展名
     * @param n    图片名
     * @param w    目标宽
     * @param per  百分比
     */
    private static String scalingPicByWidth(String path, File file, String ext, String n, int w, float per) {
        Image src;
        String img_midname = "";
        try {
            src = ImageIO.read(file); //构造Image对象

            img_midname = path + n.substring(0, n.indexOf(".")) + ext + n.substring(n.indexOf("."));
            int old_w = src.getWidth(null); //得到源图宽
            int old_h = src.getHeight(null); //得到源图高

            int new_w = old_w > w ? w : old_w;
            int new_h = old_w > w ? ((old_h * w)/old_w) : old_h;

            //图片跟据长宽留白，成一个正方形图。
            BufferedImage image_to_save;
            image_to_save = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);

            Graphics2D g = image_to_save.createGraphics();

            g.drawImage(src.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0, 0, null);
            g.dispose();

            FileOutputStream fos = new FileOutputStream(img_midname); //输出到文件流

            //新的方法
            saveAsJPEG(100, image_to_save, per, fos);
            fos.close();
        } catch (IOException ex) {
        }
        return img_midname;
    }

    public static String[] scalingPic(String path, String imgName) {
    	String [] re = new String[2];
        int width_m = 720;  //中图宽
        int width_s = 100;  //小图宽
        re[0] = scalingPicByWidth(path, new File(path + imgName), "_m", imgName, width_m, 0.7f);
        re[1] = scalingPicByWidth(path, new File(path + imgName), "_s", imgName, width_s, 1);
        return re;
    }

    public static String scalingPicM(String path, String imgName) {
        int width_m = 720;  //中图宽
        return scalingPicByWidth(path, new File(path + imgName), "_m", imgName, width_m, 0.7f);
    }


}