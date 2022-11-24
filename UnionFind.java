/*As new students begin to arrive at college, each receives a uniqu number, 1 ton. Initially, the students do not know one another, each has a different circle of friends. As the semester progresse: other groups of friends begin to form randomly.
        There will be three arrays, each aligned by an index. The first arr will contain a query Type which will be either Friend or Total. The two arrays, students 1 and students2, will each contain a student If the query type is Friend, the two students become friends. If th query type is Total, report the sum of the sizes of each group of friends for the two students.

        Example

        n=4

        queryType=['Friend', 'Friend', 'Total'] student1 = [1, 2, 1]

        student2=[2, 3, 4]

        Initial

        Friend 12 & Friend 23

        Total 14

        3-14

        The queries are assembled, aligned by index:

        Index

        1

        queryType

        studentl

        student2

        Friend Friend

        1

        2

        2

        2

        Total

        1

        3

        4

        Students will start as discrete groups (1), (2), (3) and (4). Students and 2 become friends with the first query, as well as students 2 and 3 in the second. The new groups are (1, 2), (2, 3) and (4) which simplifies to {1, 2, 3) and (4). In the third query, the number of friends for student 1 = 3 and student 4 = 1 for a Total = 4. Notice th student is indirectly part of the circle of friends of student 1


*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

public class UnionFind {


    static int arr[],cnt[];


    public static void init()
    {
        //arr is to store the parent of each element
        arr = new int[100005];
        //cnt is to count the number of elements in a group
        cnt = new int[100005];
        Arrays.fill(cnt,1);
        for(int i=0;i<100005;i++) {
            arr[i] = i;
        }
    }
//O(n^2) solution to count the number of members in a gang
    public static int count(int i)
    {
        int rootI = root(i);
        int ans=0;
        for(int p : arr)
        {
            if(root(p) == rootI)
                ans++;
        }
        return ans;
    }

//    To get the parent of i
    public static int root(int i)
    {
//        parent[i]=i => i is the special leader of that gang
        while(arr[i] != i)
            i=arr[i];
        return i;
    }
    public static void union(int[] arr, int a, int b)
    {
        //find parent of A
        int rootA = root(a);
        //find parent of B
        int rootB = root(b);
        if(rootA == rootB)
            return;

        if(cnt[rootA] > cnt[rootB])
        {
            int temp=rootA;
            rootA = rootB;
            rootB = temp;
        }
//        parent of A now becomes parent of B
        arr[rootA] = rootB;
//        since A's gang is added to B's gang, we add the count of A's gang to B's gang
        cnt[rootB]+=cnt[rootA];
    }
    public boolean find(int a, int b)
    {
//        if the roots are same, then they belong to the same group
        if(root(a) == root(b))
            return true;
        return false;
    }

    public static List<Integer> getTheGroups(int n, List<String> queryType, List<Integer> s1, List<Integer> s2)
    {
        init();
        List<Integer> ans = new ArrayList<>();
        for(int i=0;i<queryType.size();i++)
        {
            if(queryType.get(i) == "Friend")
            {
                union(arr,s1.get(i),s2.get(i));
            }
            else
            {
                int rootS1 = root(s1.get(i));
                int rootS2 = root(s2.get(i));
                if(rootS1 == rootS2)
                    ans.add(cnt[rootS1]);
                else
                    ans.add(cnt[rootS1]+cnt[rootS2]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {

        List<Integer> s1 = new ArrayList<>(Arrays.asList(5,3,4,6,3,3,2));
        List<Integer> s2 = new ArrayList<>(Arrays.asList(6,1,1,4,7,5,5));
        List<String> queryType = new ArrayList<>(Arrays.asList("Friend","Total","Friend","Total","Friend","Total","Total"));
        int n = s1.size();

        List<Integer> ans = getTheGroups(n,queryType,s1,s2);

        for (int i = 0; i <ans.size(); i++) {

            System.out.print(ans.get(i)+" ");

        }

    }
}
