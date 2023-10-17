package inzent.project.swing;

import java.io.File;
import java.util.ArrayList;

public class FileSearchAll {

	public ArrayList<String> fileSearch(String rootPath, String name, String ext) {
		// TODO Auto-generated method stub
		ArrayList<String> res = new ArrayList<String>();

		// 8. 파일 경로 가져오기
		File directory = new File(rootPath);
		File[] fList = directory.listFiles();
		String result;
		String result2;

		// null 오류가 발생한다.
		if (fList == null) {

		} else {
			for (int i = 0; i < fList.length; i++) {

				File file = fList[i];

				if (file.isFile()) {
					result = file.getAbsolutePath();
					String result4 = file.getName().substring(file.getName().lastIndexOf(".") + 1);
					if (result.contains(name)
							&& file.getName().substring(file.getName().lastIndexOf(".") + 1).contains(ext)) {
						res.add(result);
					}

				} else {
					if (file.isDirectory()) {
						result2 = file.getAbsolutePath();
						String result4 = result2.substring(result2.lastIndexOf(".") + 1);
						if (file.getAbsolutePath().contains(name)
								&& file.getName().substring(file.getName().lastIndexOf(".") + 1).contains(ext)) {
							res.add(file.getAbsolutePath());
						}

						// 9. 하위 파일을 찾는다.
						res.addAll(fileSearch(result2, name, ext));
					}
				}
			}

		}

		return res;
	}
}