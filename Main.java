public class Main {

    public static int DELAY = 10;
    public static int SLOW_DELAY = 30;
    public static int SMALL_SPACE = 25;
    public static int LARGE_SPACE = 50;
    public static String iloveyou = "I'm loving you ";

    public static void main(String[] args) {

        StringBuilder end = new StringBuilder();
        for(int way=0;way<20;way++){
            if(way==0){
                //第一阶段，不动
                end = new StringBuilder(iloveyou);
                Stay(end,10);
            }else if(way==1){
                //第二阶段，右移
                for(int i=0;i<SMALL_SPACE;i++){
                    end.insert(0," ");System.out.println(end);sleep(SLOW_DELAY);
                }
            }else if(way==2){
                //第三阶段，一个个移回去
                for(int i=SMALL_SPACE;i<SMALL_SPACE+iloveyou.length();i++){
                    if(end.charAt(i)==' ') continue;
                    for(int j=i-1;j>=i-SMALL_SPACE;j--) {
                        end.setCharAt(j,end.charAt(j+1));end.setCharAt(j+1,' ');
                        System.out.println(end);sleep(DELAY);
                    }
                }
                end.setLength(iloveyou.length());
                Stay(end,4);
            }else if(way==3){
                //消失，重现
                LeftDisappearAndMerge(end);

            }else if(way==4){
                //第四阶段，大幅度右移
                for(int i=0;i<LARGE_SPACE;i++){
                    end.insert(0," ");System.out.println(end);sleep(SLOW_DELAY);
                }
            }else if(way==5){
                //第五阶段，带间隔地左移
                LeftWithSpaceMove(end,2);
            }else if(way==6){
                //第六阶段，大幅度右移并且消失,出现再左移,消失再复原
                for(int i=0;i<LARGE_SPACE;i++){
                    end.insert(0," ");System.out.println(end);sleep(SLOW_DELAY);
                }
                for(int i=0;i<iloveyou.length();i++){
                    end.insert(0," ");end.deleteCharAt(end.length()-1);
                    System.out.println(end);sleep(SLOW_DELAY);
                }
                for(int i=0;i<iloveyou.length();i++){
                    end.append(iloveyou.charAt(i));end.deleteCharAt(0);System.out.println(end);sleep(SLOW_DELAY);
                }
                for(int i=0;i<LARGE_SPACE;i++){
                    end.deleteCharAt(0);System.out.println(end);sleep(SLOW_DELAY);
                }
                LeftDisappearAndMerge(end);
            }else if(way==7){
                //第七阶段：4遍，复原
                Stay(end,10);
                for(int i=0;i<iloveyou.length()*3;i++){
                    end.append(iloveyou.charAt(i%iloveyou.length()));
                    System.out.println(end);sleep(DELAY);
                }
                for(int i=0;i<iloveyou.length()*3;i++){
                    end.deleteCharAt(end.length()-1);
                    System.out.println(end);sleep(DELAY);
                }
            }else if(way==8){
                //第八阶段：间隔3左移+花式消失+间隔3右移
                RightWithSpaceMove(end,2);
                for(int k=0;k<3;k++)
                    for(int i=0;i<3*iloveyou.length();i++){
                        StringBuilder tmp = new StringBuilder(end);
                        for(int j=0;j<6;j++)
                            tmp.setCharAt(end.length()-1-(int)Math.floor(Math.random()*iloveyou.length()),' ');
//                        tmp.setCharAt(end.length()-1-iloveyou.length()+i,' ');
                        System.out.println(tmp);sleep(DELAY);
                    }
                LeftWithSpaceMove(end,2);

            }else if(way==9){
                //第九阶段，一个个右移
                end.deleteCharAt(0);end.append(" ");
                for(int i=0;i<SMALL_SPACE;i++)
                    end.append(" ");

                for(int i=iloveyou.length()-1;i>=0;i--){
                    for(int j=0;j<SMALL_SPACE;j++) {
                        end.setCharAt(i+j+1,end.charAt(i+j));end.setCharAt(i+j,' ');
                        System.out.println(end);sleep(DELAY);
                    }
                }
            }else if(way==10) {
                //第十阶段，左右跳舞
                for(int time=0;time<3;time++){
                    for(int i=0;i<SMALL_SPACE;i++){
                        end.deleteCharAt(0);end.insert(SMALL_SPACE+3," ");end.insert(SMALL_SPACE+iloveyou.length()-5," ");
                        System.out.println(end);sleep(DELAY);
                    }
                    for(int i=0;i<SMALL_SPACE;i++){
                        end.deleteCharAt(SMALL_SPACE+3);end.insert(0," ");end.deleteCharAt(SMALL_SPACE+iloveyou.length()-4);
                        System.out.println(end);sleep(DELAY);
                    }
                }
                //第三阶段，一个个移回去
                for(int i=SMALL_SPACE;i<SMALL_SPACE+iloveyou.length();i++){
                    if(end.charAt(i)==' ') continue;
                    for(int j=i-1;j>=i-SMALL_SPACE;j--) {
                        end.setCharAt(j,end.charAt(j+1));end.setCharAt(j+1,' ');
                        System.out.println(end);sleep(DELAY);
                    }
                }
                end.setLength(iloveyou.length());
                Stay(end,4);

            }

        }

    }

    public static void LeftDisappearAndMerge(StringBuilder end){
        for(int i=0;i<iloveyou.length();i++){
            end.deleteCharAt(0);System.out.println(end);sleep(SLOW_DELAY);
        }
        for(int i=0;i<iloveyou.length();i++){
            end.insert(0,iloveyou.charAt(iloveyou.length()-1-i));System.out.println(end);sleep(SLOW_DELAY);
        }

    }

    public static void LeftWithSpaceMove(StringBuilder end,int space){
        for(int i=0;i<LARGE_SPACE;i++){
            end.deleteCharAt(0);
            if(i/space<iloveyou.length()-1 &&i%space==0)  end.insert(end.length()-(iloveyou.length()-1-i/space)," ");
            System.out.println(end);sleep(SLOW_DELAY);
        }
//        while(end.charAt(0)==' ')
//            end.deleteCharAt(0);
        for(int i=0;i<iloveyou.length()-1;i++){
            end.deleteCharAt(i+1);
            System.out.println(end);sleep(SLOW_DELAY);
        }
    }

    public static void RightWithSpaceMove(StringBuilder end,int space){
        for(int i=0;i<iloveyou.length()-1;i++){
            end.insert(iloveyou.length()-2-i,' ');
            System.out.println(end);sleep(SLOW_DELAY);
        }
        for(int i=iloveyou.length()-1;i<LARGE_SPACE;i++){
            end.insert(0,' ');
            System.out.println(end);sleep(SLOW_DELAY);
        }
        for(int i=0;i<iloveyou.length()-1;i++){
            end.deleteCharAt(end.length()-1-i);end.insert(0," ");
            System.out.println(end);sleep(SLOW_DELAY);
        }
    }

    public static void Stay(StringBuilder end,int time){
        for(int i=0;i<time;i++){
            System.out.println(end);sleep(DELAY);
        }
    }

    public static void sleep(int delay){
        try {
            Thread.sleep(delay);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
