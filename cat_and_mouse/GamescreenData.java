package cat_and_mouse;

import java.io.Serializable;

public class GamescreenData implements Serializable {
	
	private int mouse_x;
	private int mouse_y;
	private int cat_x;
	private int cat_y;
	private int req_dx;
	private int req_dy;
	private int moused_x;
	private int moused_y;
	private String character;
	private int seconds;
	
	public int getSeconds() {
		return this.seconds;
	}
	
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	
	public int getmoused_x()
	{
		return this.moused_x;
	}
	
	public void setmoused_x(int mdx)
	{
		this.moused_x = mdx;
	}
	
	public int getmoused_y()
	{
		return this.moused_y;
	}
	
	public void setmoused_y(int mdy)
	{
		this.moused_y = mdy;
	}
	
	public int getmousereq_dx()
	{
		return this.req_dx;
	}
	
	public void setmousereq_dx(int rdx)
	{
		this.req_dx = rdx;
	}
	
	public int getmousereq_dy()
	{
		return this.req_dy;
	}
	
	public void setmousereq_dy(int rdy)
	{
		this.req_dy = rdy;
	}
	
	public void setmouseY(int y)
	{
		this.mouse_y = y;
	}
	
	public int getmouseY()
	{
		return this.mouse_y;
	}
	
	public void setmouseX(int x)
	{
		this.mouse_x = x;
	}
	
	public int getmouseX()
	{
		return this.mouse_x;
	}
	
	public void setcatY(int y) {
		this.cat_y = y;
	}
	
	public void setcatX(int x) {
		this.cat_x = x;
	}
	
	public int getcatY() {
		return this.cat_y;
	}
	
	public int getcatX() {
		return this.cat_x;
	}
	
	public int getcatd_x()
	{
		return this.moused_x;
	}
	
	public void setcatd_x(int mdx)
	{
		this.moused_x = mdx;
	}
	
	public int getcatd_y()
	{
		return this.moused_y;
	}
	
	public void setcatd_y(int mdy)
	{
		this.moused_y = mdy;
	}
	
	public String getCharacter() {
		return this.character;
	}
	
	public void setCharacter(String character) {
		this.character = character;
	}
	
	public int getcatreq_dx()
	{
		return this.req_dx;
	}
	
	public void setcatreq_dx(int rdx)
	{
		this.req_dx = rdx;
	}
	
	public int getcatreq_dy()
	{
		return this.req_dy;
	}
	
	public void setcatreq_dy(int rdy)
	{
		this.req_dy = rdy;
	}

}
