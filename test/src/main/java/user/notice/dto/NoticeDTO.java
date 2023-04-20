package user.notice.dto;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class NoticeDTO {
	
	private int num;
	private char fix; // 중요공지여부 (Y/N)
	private String title;
	private String content;
	private String upload_file; //파일 첨부
	private String upload_img; //사진 첨부
	private Date reg_date;
	private int readcount;
	
	//로그인 세션 처리 시 수정할 예정
	private String admin_id;
	private String admin_name;
	
	private String datecheck; //작성날짜 확인용  //게시판 list에 New 띄울건데 안쓰면 지워도됨
	
	private int prenum; //이전글 번호
	private String presub; //이전글 제목
	private int nextnum; //이전글 번호
	private String nextsub; //다음글 제목
	
	//write페이지에서 파일첨부를 받아 처리해주는 멤버변수
	private MultipartFile filename;
	//write페이지에서 사진첨부를 받아 처리해주는 멤버변수
	private MultipartFile imgname;
	
	
	public NoticeDTO() {
		// TODO Auto-generated constructor stub
	}

	//mainPage용 생성자
	public NoticeDTO(int num, String title) {
		super();
		this.num = num;
		this.title = title;
	}
	
	public NoticeDTO(int num, char fix, String title, String content, String upload_file, String upload_img,
			Date reg_date, int readcount, String admin_id, String admin_name, String datecheck, int prenum,
			String presub, int nextnum, String nextsub, MultipartFile filename, MultipartFile imgname) {
		super();
		this.num = num;
		this.fix = fix;
		this.title = title;
		this.content = content;
		this.upload_file = upload_file;
		this.upload_img = upload_img;
		this.reg_date = reg_date;
		this.readcount = readcount;
		this.admin_id = admin_id;
		this.admin_name = admin_name;
		this.datecheck = datecheck;
		this.prenum = prenum;
		this.presub = presub;
		this.nextnum = nextnum;
		this.nextsub = nextsub;
		this.filename = filename;
		this.imgname = imgname;
	}



	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public char getFix() {
		return fix;
	}

	public void setFix(char fix) {
		this.fix = fix;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUpload_file() {
		return upload_file;
	}

	public void setUpload_file(String upload_file) {
		this.upload_file = upload_file;
	}

	public String getUpload_img() {
		return upload_img;
	}

	public void setUpload_img(String upload_img) {
		this.upload_img = upload_img;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public String getDatecheck() {
		return datecheck;
	}

	public void setDatecheck(String datecheck) {
		this.datecheck = datecheck;
	}

	public int getPrenum() {
		return prenum;
	}

	public void setPrenum(int prenum) {
		this.prenum = prenum;
	}

	public String getPresub() {
		return presub;
	}

	public void setPresub(String presub) {
		this.presub = presub;
	}

	public int getNextnum() {
		return nextnum;
	}

	public void setNextnum(int nextnum) {
		this.nextnum = nextnum;
	}

	public String getNextsub() {
		return nextsub;
	}

	public void setNextsub(String nextsub) {
		this.nextsub = nextsub;
	}

	public MultipartFile getFilename() {
		return filename;
	}

	public void setFilename(MultipartFile filename) {
		this.filename = filename;
	}

	public MultipartFile getImgname() {
		return imgname;
	}

	public void setImgname(MultipartFile imgname) {
		this.imgname = imgname;
	}

	

}
