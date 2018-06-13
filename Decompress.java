package decompress;

/**
 *
 * @author Maverick
 */
public class Decompress {

    private String decompress(String input) throws Exception {
        String value;
        int lpos, fpos;
        while((lpos=input.indexOf("]"))>-1){
            value="";
            fpos=input.substring(0, lpos).lastIndexOf("[");
            if(fpos==-1){
                throw new Exception("Invalid input: '[' expected but not found.");
            }else{
                String innerVal = input.substring(fpos+1, lpos);
                int num=0;
                int k=0;
                int mul=1;
                for(int i=fpos-1;i>=0;i--){
                    char ch=input.charAt(i);
                    if(Character.isDigit(ch)){
                        num=num+(Character.getNumericValue(ch)*mul);
                        mul=mul*10;
                        k++;
                    }else{
                        break;
                    }
                }
                if(k==0){
                    throw new Exception("Invalid input: Number to denote repetition is missing.!");
                }
                for(int i=0;i<num;i++){
                    value+=innerVal;
                }
                if(lpos+1<input.length()){
                    input=input.substring(0, fpos-k)+value+input.substring(lpos+1);
                }else{
                    input=input.substring(0, fpos-k)+value;
                }
                
            }
        }
        return input;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Decompress dc = new Decompress();
            System.out.println("akttkkttkkttkabppdbppdc".equals(dc.decompress("a3[k2[t]k]a2[b2[p]d]c")));
            System.out.println("akkkkkkkkkkkkkkkkkkkkc".equals(dc.decompress("a20[k]c")));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}