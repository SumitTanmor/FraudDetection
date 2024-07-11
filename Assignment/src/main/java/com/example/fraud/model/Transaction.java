package com.example.fraud.model;

import java.time.LocalDateTime;

public class Transaction {
    private Long id;
    private Long userId;
    private Double amount;
    private LocalDateTime time;

    public Transaction() {
        this.time = LocalDateTime.now();
    }

    public Transaction(Long id, Long userId, Double amount) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.time = LocalDateTime.now();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

    
}
