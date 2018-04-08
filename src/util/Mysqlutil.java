package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Mysqlutil {
	public static void backup(String path, String backupFile) throws IOException {
		String commandFormat = "\"%s/bin/mysqldump.exe\" -u%s -p%s -hlocalhost -P%d %s -r \"%s\"";

		String command = String.format(commandFormat, path, DBUtil.loginName, DBUtil.password, DBUtil.port,
				DBUtil.database, backupFile);
		Runtime.getRuntime().exec(command);
	}

	public static void recover(String path, String recoverFile) throws IOException {

		String commandFormat = "\"%s/bin/mysql.exe\" -u%s -p%s %s";
		String command = String.format(commandFormat, path, DBUtil.loginName, DBUtil.password, DBUtil.database);
		Process p = Runtime.getRuntime().exec(command);
		OutputStream out = p.getOutputStream();
		String inStr;
		StringBuffer sb = new StringBuffer("");
		String outStr;
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(recoverFile), "utf8"));
		while ((inStr = br.readLine()) != null) {
			sb.append(inStr + System.getProperty("line.separator"));
		}
		outStr = sb.toString();

		OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
		writer.write(outStr);
		writer.flush();
		br.close();
		out.close();
		writer.close();

	}

	// public static void main(String[] args) {
	// String path = "000";
	// String backupFile = path;
	// String commandFormat = "\"%s/bin/mysqldump.exe\" -u%s -p%s -hlocalhost -P%d
	// %s -r \"%s\"";
	//
	// String command = String.format(commandFormat, path, DBUtil.loginName,
	// DBUtil.password, DBUtil.port,
	// DBUtil.database, backupFile);
	// System.out.println(command);
	// }
}
