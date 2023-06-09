package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.TodoDTO;


// 매퍼에 있는 이름을 찾아서 실행한다. 별도의 클래스를 설정하지 않아도 된다.
@Mapper
@Repository
public interface TodoDAO {
	public List<TodoDTO> getTodoList() throws Exception;
	public int insertTodoList(TodoDTO dto) throws Exception; // id는 시퀀스로 받아오고, ?는 0이고 Todoname만 받아오면 된다.
	public int updateTodoList(TodoDTO dto) throws Exception;
	public int deleteTodoList(int id) throws Exception;

}
