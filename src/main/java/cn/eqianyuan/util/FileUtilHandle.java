package cn.eqianyuan.util;

import cn.eqianyuan.core.exception.EqianyuanException;
import cn.eqianyuan.core.exception.ExceptionMsgConstant;
import cn.eqianyuan.util.yamlMapper.SystemConf;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtilHandle {

    private static Logger logger = Logger.getLogger(FileUtilHandle.class);

    //获取附件上传位置
    static String absoluteDirectory = SessionUtil.getSession().getServletContext().getRealPath("/");

    /**
     * 多个附件上传
     *
     * @param multiFiles
     * @return
     * @throws EqianyuanException
     */
    public static List<Map<String, Object>> upload(MultiValueMap<String, MultipartFile> multiFiles) throws EqianyuanException {
        if (CollectionUtils.isEmpty(multiFiles)) {
            logger.info("upload fail , because file no exists");
            throw new EqianyuanException(ExceptionMsgConstant.FILE_NO_EXISTS);
        }

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (String key : multiFiles.keySet()) {
            MultipartFile file = multiFiles.getFirst(key);
            Map<String, Object> resultMap = upload(file);
            resultList.add(resultMap);
        }

        return resultList;
    }

    /**
     * 单个附件上传
     *
     * @param multipartFile
     * @return
     * @throws EqianyuanException
     */
    public static Map<String, Object> upload(MultipartFile multipartFile) throws EqianyuanException {
        if (ObjectUtils.isEmpty(multipartFile)) {
            logger.info("upload fail , because file no exists");
            throw new EqianyuanException(ExceptionMsgConstant.FILE_NO_EXISTS);
        }

        if (StringUtils.isEmpty(multipartFile.getOriginalFilename())) {
            logger.info("upload fail , because file no exists");
            throw new EqianyuanException(ExceptionMsgConstant.FILE_NO_EXISTS);
        }

//        //获取附件上传位置
//        String absoluteDirectory = SessionUtil.getSession().getServletContext().getRealPath("/");
        //定义附件上传目录
        String fileUploadPath = absoluteDirectory + SystemConf.DEMAND_USER_LOGO_FILE_UPLOAD_PATH.toString();

        File f1 = new File(fileUploadPath);
        if (!f1.exists()) {
            f1.mkdirs();
        }

        //定义完整上传路径（上传目录+文件名）
        String fullPath = fileUploadPath + File.separator + multipartFile.getOriginalFilename();
        // 附件处理上传
        try {
            multipartFile.transferTo(new File(fullPath));
        } catch (IOException e) {
            logger.error("upload fail ", e);
            throw new EqianyuanException(ExceptionMsgConstant.FILE_UPDATE_ERROR);
        }

        //申明返回MAP对象
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("fileName", multipartFile.getOriginalFilename());
        resultMap.put("filePath", fullPath);
        return resultMap;
    }

    /**
     * 附件删除
     *
     * @return
     */
    public static void deleteFile(String filePath) throws EqianyuanException {
        if (StringUtils.isEmpty(filePath)) {
            logger.info("deleteFile fail , because filePath is empty");
            return;
        }

        File file = new File(filePath);
        if (!file.exists()) {
            logger.info("deleteFile fail , because file no exists");
            return;
        }

        if (!file.delete()) {
            logger.info("deleteFile fail , because file delete error");
        }
    }

    /**
     * 文件写入
     *
     * @param fileStream 二进制流
     * @param fileName   文件名称
     * @return
     */
    public static boolean writeFile(byte[] fileStream, String filePath, String fileName) {
        OutputStream fileOutputStream = null;
        File fileDirectory = new File(absoluteDirectory + filePath);
        if (!fileDirectory.isDirectory()) {
            if (!fileDirectory.mkdir()) {
                return false;
            }
        }

        try {
            fileOutputStream = new FileOutputStream(fileDirectory.getPath() + File.separator + fileName);
            fileOutputStream.write(fileStream);
            return true;
        } catch (FileNotFoundException e) {
            logger.error("writeFile fail", e);
        } catch (IOException e) {
            logger.error("writeFile fail", e);
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                logger.error("writeFile fail", e);
            }
        }
        return false;
    }

}
