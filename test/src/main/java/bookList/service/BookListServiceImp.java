package bookList.service;

import java.util.List;

import bookList.dao.BookListDAO;
import bookList.dto.BookListDTO;
import bookList.dto.BookReviewDTO;
import bookList.dto.PageDTO;

public class BookListServiceImp implements BookListService{

	private BookListDAO bookListDao;
	
	public BookListServiceImp() {
		
	}
	
	public void setBookListDao(BookListDAO bookListDao) {
		this.bookListDao = bookListDao;
	}
	
	// ISBN으로 book 가져오기
	@Override
	public BookListDTO selectBookProcess(String isbn) {
		return bookListDao.selectBook(isbn);
	}
	
	@Override
	public int revCheckProcess(String keynum, String book_keynum) {
		return bookListDao.revCheck(keynum,book_keynum);
	}
	
	@Override
	public int countProcess(String isbn) {
		return bookListDao.count(isbn);
	}

	@Override
	public List<BookReviewDTO> reviewListProcess(PageDTO pv, String isbn) {
		return bookListDao.reviewList(pv,isbn);
	}

	@Override
	public void saveProcess(String keynum,BookReviewDTO dto) {
		bookListDao.save(keynum,dto);
	}

	@Override
	public void updateProcess(BookReviewDTO dto) {
		bookListDao.update(dto);
	}

	@Override
	public void deleteProcess(int num) {
		bookListDao.delete(num);
	}
	
	@Override
	public String getKeynumProcess(String id) {
		// TODO Auto-generated method stub
		return bookListDao.getKeynum(id);
	}
	
	@Override
	public String getIdProcess(String keynum) {
		// TODO Auto-generated method stub
		return bookListDao.getId(keynum);
	}
	
	@Override
	public int countService() {
		// TODO Auto-generated method stub
		return bookListDao.count();
	}
	




}
