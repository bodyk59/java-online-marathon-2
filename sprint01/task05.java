class Point {
    private int x;
    private int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int[] getXYPair() {
        return new int[] {x, y};
    }
    
    public double distance(int x, int y) {
        return Math.hypot((x - this.x), (y - this.y));
    }
    
    public double distance(Point point) {
        int[] pair = point.getXYPair();
        return Math.hypot((pair[0] - x), (pair[1] - y));
    }
    
    public double distance() {
        return Math.hypot((-x), (-y));
    }
}
