package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEmail {

	private String subject;
	private String recipient;
	private String body;
}
