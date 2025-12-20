public interface VisitorService {
    Visitor save(Visitor visitor);
    List<Visitor> getAll();
    void delete(Long id);
}
