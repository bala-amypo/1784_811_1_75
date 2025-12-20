@Service
public class VisitorServiceImpl implements VisitorService {

    private final VisitorRepository repository;

    public VisitorServiceImpl(VisitorRepository repository) {
        this.repository = repository;
    }

    public Visitor save(Visitor visitor) {
        return repository.save(visitor);
    }

    public List<Visitor> getAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
