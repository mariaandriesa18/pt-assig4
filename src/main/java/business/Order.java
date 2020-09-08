package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The table number should be a positive integer
 *
 *  @inv  tableNb > 0 && tableNb <= 55
 */
public class Order implements Serializable{
	private static Integer idCount = 0;
	private Integer oid;
	private LocalDateTime date;
	private Integer tableNb;
	
	public Order() {}
	public Order( Integer tbNb) {
		this.oid = ++idCount;
		this.date = LocalDateTime.now();
		this.tableNb = tbNb;
	}
	
	
	@Override
	public int hashCode() {
		int prime = 97;
		int res = 769;
		
		res = prime * res + ((oid == null) ? 0 : oid.hashCode());
		res = prime * res + ((date == null) ? 0 : date.hashCode());
		res = prime * res + ((tableNb == null) ? 0 : tableNb.hashCode());
		return res;
	}
	@Override
	public boolean equals(Object arg0) {
			
		if (this == arg0) return true;
		if(arg0 == null) return false;
		if(this.getClass() != arg0.getClass()) return false;
		Order ord = (Order) arg0;		
		return (this.oid == ord.getOid() && this.date.equals(ord.getDate()) && this.tableNb == ord.getTableNb());
	}


	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}


	public LocalDateTime getDate() {
		return date;
	}


	public void setDate(LocalDateTime date) {
		this.date = date;
	}


	public Integer getTableNb() {
		return tableNb;
	}


	public void setTableNb(Integer tableNb) {
		this.tableNb = tableNb;
	}
	
	
}
