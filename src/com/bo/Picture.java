package com.bo;
import com.bo.Point;
public class Picture {

	//Constructors
	public Picture(){}
	
	public Picture(String url, Point size, Point origin, Point anchor)
	{
		_url=url;
		_size=size;
		_origin=origin;
		_anchor=anchor;
	}
	
	private String _url;
	
    // This marker is 20 pixels wide by 32 pixels high.
    private Point _size;
    
    // The origin for this image is (0, 0).
    private Point _origin;
    
    // The anchor for this image is the base of the flagpole at (0, 32).
    private Point _anchor;


    // GETTERS & SETTERS
    public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		this._url = url;
	}

	public Point getSize() {
		return _size;
	}

	public void setSize(Point size) {
		this._size = size;
	}

	public Point getOrigin() {
		return _origin;
	}

	public void setOrigin(Point origin) {
		this._origin = origin;
	}

	public Point getAnchor() {
		return _anchor;
	}

	public void setAnchor(Point anchor) {
		this._anchor = anchor;
	}
}
