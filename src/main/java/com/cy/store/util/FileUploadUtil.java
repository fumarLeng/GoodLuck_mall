//package com.cy.store.util;
//
//import com.cy.store.controller.ex.FileUploadException;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//public class FileUploadUtil  {
//
//    // 上传文件的目录路径硬编码在工具类中
//    private static final String UPLOAD_DIR = "C:/Users/T14 Gen 3/Desktop/GoodLuck_mall/src/main/resources/static/images/portal";
//
//    // 限制文件上传的大小
//    private static final int AVATAR_MAX_SIZE = 9 * 1024 * 1024;
//    // 限制上传文件类型
//    private static final List<String> AVATAR_TYPE = new ArrayList<>();
//
//    static {
//        AVATAR_TYPE.add("image/jpeg");
//        AVATAR_TYPE.add("image/png");
//        AVATAR_TYPE.add("image/bmp");
//        AVATAR_TYPE.add("image/gif");
//    }
//
//    /**
//     * 上传文件并返回文件的存储路径
//     *
//     * @param file MultipartFile文件对象
//     * @return 文件的存储路径
//     * @throws FileUploadException 如果上传文件时发生错误
//     */
//    public static String uploadFile(MultipartFile file) throws FileUploadException {
//        // 检查文件是否为空
//        if (file.isEmpty()) {
//            throw new FileUploadException("没有文件被上传");
//        }
//        // 检查文件大小
//        if (file.getSize() > AVATAR_MAX_SIZE) {
//            throw new FileUploadException("文件大小超过限制");
//        }
//        // 检查文件类型
//        String contentType = file.getContentType();
//        if (!AVATAR_TYPE.contains(contentType)) {
//            throw new FileUploadException("文件类型错误");
//        }
//
//        // 创建上传目录的File对象
//        File dir = new File(UPLOAD_DIR);
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//
//        // 生成随机目录
//        String randomDir = UUID.randomUUID().toString();
//        File parentDir = new File(UPLOAD_DIR, randomDir);
//        if (!parentDir.exists()) {
//            parentDir.mkdirs();
//        }
//
//        // 获取原始文件名并生成新的文件名
//        String originalFilename = file.getOriginalFilename();
//        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
//        String filename = "collect" + extension;
//
//        // 创建目标文件对象
//        File dest = new File(parentDir, filename);
//        try {
//            // 保存文件
//            file.transferTo(dest);
//        } catch (IOException e) {
//            throw new FileUploadException("文件上传异常");
//        }
//
//        // 返回文件存储路径
//        return "/images/portal/" + randomDir + "/";
//    }
//
//}
//
//
