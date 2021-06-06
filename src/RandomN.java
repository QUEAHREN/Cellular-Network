public interface RandomN {
    //生成[l, r]随机数
    public int randomNumber(int l, int r);
    //主要用于二选一的随机操作(50%概率)
    public boolean randomChoice();
}

