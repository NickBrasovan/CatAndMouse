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
	
	public int getreq_dx()
	{
		return this.req_dx;
	}
	
	public void setreq_dx(int rdx)
	{
		this.req_dx = rdx;
	}
	
	public int getreq_dy()
	{
		return this.req_dy;
	}
	
	public void setreq_dy(int rdy)
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
	
	

}
