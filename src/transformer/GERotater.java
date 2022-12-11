package transformer;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;

import shapes.*;

public class GERotater extends GETransformer {
	private ArrayList<GEShape> shapelist;
	private Point2D.Double ROrigin;
	private double theta;

	public GERotater(GEShape shape) {
		super(shape);
		if(shape instanceof GEGroup){
			shapelist = new ArrayList<GEShape>();
			for(GEShape childshape : ((GEGroup) shape).getChildList()){
				shapelist.add(childshape);
			}
		}
	}

	@Override
	public void transformer(Graphics2D g2d, Point p) {
		g2d.setXORMode(g2d.getBackground());
		g2d.setStroke(basicStroke);
		double theta2 = theta - Math.atan2(ROrigin.y- p.getY(), p.getX() - ROrigin.x);
		if(shape instanceof GEGroup){
			GEShape temp;
			for(int i = 0; i < shapelist.size(); i++){
				temp = shapelist.get(i);
				temp.draw(g2d);
				temp.rotaterCoordinate(theta2, ROrigin);
				temp.draw(g2d);
			}
		}else{
			shape.draw(g2d);
			shape.rotaterCoordinate(theta2, ROrigin);
			shape.draw(g2d);
		}
		theta = Math.atan2(ROrigin.y - p.getY(), p.getX() - ROrigin.x);
	}
	

	@Override
	public void init(Point p) {
		ROrigin = new Point2D.Double(shape.getBounds().getCenterX(), shape.getBounds().getCenterY());
		theta = Math.atan2(ROrigin.y - p.getY(), ROrigin.x - p.getX());
	}
	@Override
	public void shift_transformer(Graphics2D g2d, Point p, boolean shift_key) {
		// TODO Auto-generated method stub
		
	}

}
