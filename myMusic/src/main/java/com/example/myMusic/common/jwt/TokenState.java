package com.example.myMusic.common.jwt;

/**
 * 枚举, 定义 token 的三种状态
 */
public enum TokenState {

	EXPIRED("EXPIRED"), // 过期
	INVALID("INVALID"), // token 不合法
	VALID("VALID"); // 有效的

	private String state;

	private TokenState(String state) {
		this.state = state;
	}

	/*
	 * 根据状态字符串获取token状态枚举对象
	 */
	public static TokenState getTokenState(String tokenState) {

		TokenState[] states = TokenState.values();
		TokenState ts = null;

		for (TokenState state : states) {

			if (state.toString().equals(tokenState)) {
				ts = state;
				break;
			}

		}

		return ts;

	}

	public String toString() {
		return this.state;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
