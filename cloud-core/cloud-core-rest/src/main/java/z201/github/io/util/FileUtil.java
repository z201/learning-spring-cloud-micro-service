package z201.github.io.util;

import z201.github.io.exception.EonerException;
import z201.github.io.exception.EonerExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.UUID;

public class FileUtil {
	
	private static Logger log = LoggerFactory.getLogger(FileUtil.class);

	/**
	 * NIO way
	 */
	public static byte[] toByteArray(String filename) {

		File f = new File(filename);
		if (!f.exists()) {
			log.error("文件未找到！" + filename);
			throw new EonerException(EonerExceptionEnum.FILE_NOT_FOUND);
		}
		FileChannel channel = null;
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(f);
			channel = fs.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
			while ((channel.read(byteBuffer)) > 0) {
				// do nothing
				// System.out.println("reading");
			}
			return byteBuffer.array();
		} catch (IOException e) {
			throw new EonerException(EonerExceptionEnum.FILE_READING_ERROR);
		} finally {
			try {
				channel.close();
			} catch (IOException e) {
				throw new EonerException(EonerExceptionEnum.FILE_READING_ERROR);
			}
			try {
				fs.close();
			} catch (IOException e) {
				throw new EonerException(EonerExceptionEnum.FILE_READING_ERROR);
			}
		}
	}


	/*
     * 获取唯一的文件名
     */
	public static String getUniqueFileName()
	{
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/*
     * 验证是否是文件
     */
	public static boolean isFile(String fileName)
	{
		if (!checkFileName(fileName))
		{
			return false;
		}
		return new File(fileName).isFile();
	}

	/*
     * 获取文件名后缀
     */
	public static String getFileExt(String fileName)
	{
		if (!checkFileName(fileName))
		{
			return "";
		}
		return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
	}

	/*
     * 获取文件大小
     */
	public static long getFileSize(String fileName)
	{
		if (!checkFileName(fileName))
		{
			return 0L;
		}
		long lSize = 0L;
		try
		{
			File file = new File(fileName);
			if (file.exists())
			{
				if (file.isFile() && file.canRead())
				{
					lSize = file.length();
				}
				else
				{
					lSize = -1;
				}
			}
			else
			{
				lSize = 0;
			}
		}
		catch (Exception e)
		{
			lSize = -1;
		}
		return lSize;
	}

	/*
     * 检验文件名
     */
	private static boolean checkFileName(String fileName)
	{
		if (fileName == null || fileName.equals("") || fileName.lastIndexOf(".") == -1)
		{
			return false;
		}
		return true;
	}

	/*
     * 创建文件
     */
	public static boolean createFile(String fileName)
	{
		if (!checkFileName(fileName))
		{
			return false;
		}
		File file = new File(fileName);
		if (file.exists())
		{
			return false;
		}
		if (file.getName().endsWith(File.separator))
		{
			return false;
		}
		//判断目标文件所在的目录是否存在
		if (!file.getParentFile().exists())
		{
			//如果目标文件所在的目录不存在，则创建父目录
			if (!file.getParentFile().mkdirs())
			{
				return false;
			}
		}
		//创建目标文件
		try
		{
			if (file.createNewFile())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/*
     *  复制文件
     */
	public static boolean copyFile(String srcFileName, String destFileName)
	{
		if (!checkFileName(srcFileName))
		{
			return false;
		}
		File srcFile = new File(srcFileName);
		// 判断源文件是否存在
		if (!srcFile.exists())
		{
			return false;
		}
		else if (!srcFile.isFile())
		{
			return false;
		}

		// 判断目标文件是否存在
		if (!createFile(destFileName))
		{
			return false;
		}

		// 复制文件
		int byteRead = 0;
		InputStream in = null;
		OutputStream out = null;
		try
		{
			in = new FileInputStream(srcFile);
			out = new FileOutputStream(destFileName);
			byte[] buffer = new byte[1024];
			while ((byteRead = in.read(buffer)) != -1)
			{
				out.write(buffer, 0, byteRead);
			}
			return true;
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return false;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			try
			{
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/*
     * 删除文件
     */
	public static boolean deleteFile(String fileName)
	{
		boolean isSuccess = true;
		try
		{
			File file = new File(fileName);
			if (file.exists())
			{
				isSuccess = file.delete();
			}

		}
		catch (Exception e)
		{
			isSuccess = false;
		}

		return isSuccess;
	}

}