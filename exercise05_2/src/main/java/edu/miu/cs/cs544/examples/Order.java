package edu.miu.cs.cs544.examples;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"customer"})
@Table(name = "OrderTable")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String orderid;
	private LocalDate date;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="customer_Id")
	private Customer customer;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name="order_id")
	private List<OrderLine> ol =new ArrayList<OrderLine>();
	
	public void addOrderLine(OrderLine orderLine){
		ol.add(orderLine);
    }


}
