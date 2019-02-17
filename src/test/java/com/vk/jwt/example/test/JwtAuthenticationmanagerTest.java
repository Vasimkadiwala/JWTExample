package com.vk.jwt.example.test;

import org.junit.Assert;
import org.junit.Test;

import com.vk.jwt.example.JwtAuthenticationManager;

public class JwtAuthenticationmanagerTest {

	
	
	@Test
	public void test() throws Exception {
		
		String token="eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJTYW1wbGUgQ2xpbmV0IElkIiwiZXhwIjoxNTUwMzQxNTI0fQ==.OAC0W1P864IxNayP1q4QW7S4rMCdBJdpljBqFbTo2wRB5WKU6ryIIdRZTN3Y30AjY8OcQrH52RMjokCr+ZexZieRRwkQ4dMoSuy+mt6zaS259Imd7ceIYLSoGBVFeMCkjwF6ZVdk7eb800C/fEdlJQ1bEbyypdch8tYh2HZVGyRZkGEQioJd3is9QVUnoVdpQ2hBmLRr/eXAERaaQWlWHfLHlCZP4tygLfBivUHiseA3yxNdh8XFhvUQG0JpL6r5zFrM2TaHJo7BifpJOPH/tCp78BqKhOYoS8Abu17BBW0u2sg/kzFdOi55mmjPr3s5vZ3ZffqarEeCyhDSrrDU+A==";
		
		
		Assert.assertTrue(new JwtAuthenticationManager().validateJwtToken(token));
	}
}
