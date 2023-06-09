package com.example.demo.controller;


import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TodoDTO;
import com.example.demo.service.TodoService;


// 프론트에서 어떤한 번호로 요청을 오든 허용을 해준다.
@CrossOrigin("*")
//@CrossOrigin({"http://localhost:3000"})

@RestController // @Controller + @ResponseBody
//@Controller // 컨트롤러 추가.. 프론트엔드가 리액트. 값을 json으로 내보내야 함. nextapi를 처리를 해야 함.
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	public TodoController() {
		
	}
	
	// http://localhost:8090/todo/all
	//@ResponseBody
	@GetMapping("/todo/all")
	public List<TodoDTO> getList() throws Exception{
		return todoService.search();
	}
	
	// post 방식을 사용해야 한다.
	// http://localhost:8090/todo
//	public int postTodo(@RequestBody TodoDTO dto) throws Exception{
//		int chk = todoService.insert(dto);
//		return chk;
//	}

	// 오브젝트로 같으면 아래 엔티티에서 또 선언을 안해줘도 된다.
	@PostMapping("/todo")
	public ResponseEntity<Object> postTodo(@RequestBody TodoDTO dto) throws Exception{
		int chk = todoService.insert(dto);
		
		HashMap<String, String> map = new HashMap<>();
		map.put("createDate", new Date().toString());
		map.put("message", "insert Ok");
		map.put("cnt", String.valueOf(chk));
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
		
		if(chk>=1) {
			// return new ResponseEntity<Object>(HttpStatus.OK);
			// return new ResponseEntity<Object>(map,HttpStatus.OK);
			return new ResponseEntity<Object>(map, header, HttpStatus.OK); // 값을 담아서 넘겨야 한다.
		}else {
			return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);
			
		}

	}
	
	// 1번에 있는 값을 1번 또는 0으로 넘어올 수 있다?..이거 맞음?
	// http://localhost:8090/todo/1/0
	@PutMapping("/todo/{id}/{completed}")
	//                                        여기서 넘어오는 값을 선어하겠다.
	public ResponseEntity<Object> putTodo(@PathVariable("id") int id,
										  @PathVariable("completed") int completed) throws Exception{
		TodoDTO dto = new TodoDTO();
		dto.setId(id);
		dto.setCompleted(completed==0?1:0);
		todoService.update(dto);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	// http://localhost:8090/todo/1
	@DeleteMapping("/todo/{id}")
	public ResponseEntity<Object> deleteTodo(@PathVariable("id") int id) throws Exception{
		
		todoService.delete(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
} // end class












