package admin.borrow.dao;

import java.util.List;

import admin.borrow.dto.BorrowDTO;
import print.dto.PageDTO;

public interface BorrowDAO {
	public List<BorrowDTO> printBorrow(PageDTO pv);
	public int countBorrow();
	public int countSearchBorrow(String option, String query);
	public List<BorrowDTO> printSearchBorrow(PageDTO pv,String option, String query);
}
