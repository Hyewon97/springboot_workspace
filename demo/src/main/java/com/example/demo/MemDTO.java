package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor // 파라미터가 없는 생성자
@AllArgsConstructor // 모든 멤버변수를 제공한ㄴ 생성자

@Getter // 게터
@Setter // 세터
public class MemDTO {
	private String name;
	private int age;
	private String loc;
	
	

}
