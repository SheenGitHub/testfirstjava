package sample;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/7/9.
 */
@Author(name="Raoul")
@Author(name="Mario")
@Author(name="Alan")
public class Book {
    @NotNull public String name;

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        Book b = new Book();
        if(b.getName() == null)
            System.out.println(b.name);
        Author[] authors = Book.class.getAnnotationsByType(Author.class);
        Arrays.asList(authors).forEach(a-> {System.out.println(a.name());});
    }
}
