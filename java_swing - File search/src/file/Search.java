package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Search {
	
	
	public static void main(String[] args) {
		// 파일을 관리할 폴더부터 만들기
		final String path = "C:\\Users\\정지민\\";
		File folder = new File(path);
		folder.mkdir();
		folder.deleteOnExit();
		System.out.println("builded directory!");		
		
		while ( true ) {
			System.out.println("here");
			File[] fileList = folder.listFiles();

			Scanner scan = new Scanner(System.in);
			String command = scan.nextLine();
			
			if ( command.equals("commands") ) {
				System.out.println("==명령어 리스트를 출력합니다.==");
				System.out.println("commands : 명렁어리스트\r\n" + 
						"list : 문서리스트\r\n" + 
						"create : 문서생성\r\n" + 
						"delete : 문서삭제\r\n" + 
						"view : 문서보기\r\n" + 
						"exit : 종료");
			} else if ( command.equals("create") ) {
				System.out.println("==문서생성==");
				
				System.out.println("파일명을 입력해주세요(취소 : -1)");
				
				String name = scan.nextLine();
				if ( name.equals("-1") ) { continue; }
				
				System.out.println("내용을 입력해주세요(취소 : -1)");
				
				String content = scan.nextLine();
				if ( content.equals("-1") ) { continue; }
				
				try {
					// 파일 생성하기
					File file = new File( path + name );
					file.deleteOnExit();
					FileWriter fw = new FileWriter(file, true);
					fw.write(content);
					fw.flush();
					fw.close();
					System.out.println("Create a file. ( in " + path + " ) ");
				} catch ( IOException e ) {
					System.out.println("파일 생성 에러");
					System.out.println(e);
				}

			} 
			
			else if ( command.equals("list") ) {
				System.out.println("==문서 리스트를 출력합니다.==");
				
				System.out.println("파일명          용량");
				for(int i = 0 ; i < fileList.length ; i++){
					File f = fileList[i]; 
				
					if(f.isFile()){// 파일이 있다면 파일 이름 출력
						System.out.printf("%-15s %.3f kb \n",f.getName(),((double)f.length())/(double)1000);
						// System.out.println(f.getName() + "  " + ((double)f.length())/(double)1000 + "kb");
					}
				}
				if ( fileList.length < 1 ) {
					System.out.println("문서가 없습니다.");
				}
			} 
			
			else if ( command.equals("delete") ) {
				System.out.println("==문서삭제==");
				System.out.println("파일명을 입력해주세요(취소 : -1)");
				
				String name = scan.nextLine();
				if ( name.equals("-1") ) { continue; } 

				for(int i = 0 ; i < fileList.length ; i++){
					File f = fileList[i]; 
					if(f.getName().equals(name) ) {
						if ( f.exists() ){// 파일이 있다면 파일 이름 출력
							System.gc();
							if ( f.delete() ) {
								System.out.println(name + " 파일이 삭제되었습니다.");
								break;
							} else {
								System.out.println("해당 문서를 삭제할 수 없습니다.");
								break;
							}
						}
					} else {
						System.out.println("해당 문서가 없습니다.");
						break;
					}

				}
			} else if ( command.equals("view") ) {
				System.out.println("==문서보기==");
				System.out.println("파일명을 입력해주세요(취소 : -1)");
				
				String name = scan.nextLine();
				if ( name.equals("-1") ) { continue; } 
				
				System.out.println(fileList.length);
				int Num = 0;
				for(int i = 0 ; i < fileList.length ; i++){
					File f = fileList[i]; 
					
					if(f.getName().contains(name)){ // 파일이 있다면 파일 이름 출력
						if ( f.exists() ) {
							try {
								System.out.println("해당 파일 이름 : " + f.getName());
								System.out.println("해당 파일 경로  : " + f.getAbsolutePath());
								System.out.println("\n");
								Num++;
								Scanner reader = new Scanner(f);

							
							}catch (FileNotFoundException e) {
				                // TODO: handle exception
				            }
						
						}
					
					} 
					else {
						
					}
					
					if ( i == fileList.length ){
						System.out.println("문서가 없습니다.");
						break;
					}
					
				}
				System.out.println("해당되는 문서 개수 : " + Num); 
			} else if ( command.equals("exit") ) {
				System.out.println("파일 관리 프로그램을 정말로 종료하시겠습니까? y/n");
				String re_command = scan.nextLine();
				if ( re_command.equals("y") ) {
					break;
				} else { continue; } // 없어도 되나?
			}
		}
		
		System.out.println("파일 관리 프로그램을 종료합니다..");
	}
}