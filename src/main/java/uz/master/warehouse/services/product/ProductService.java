package uz.master.warehouse.services.product;

import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.master.warehouse.criteria.ProductCriteria;
import uz.master.warehouse.dto.product.ProductCreateDto;
import uz.master.warehouse.dto.product.ProductDto;
import uz.master.warehouse.dto.product.ProductUpdateDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.entity.product.Product;
import uz.master.warehouse.mapper.product.ProductMapper;
import uz.master.warehouse.repository.product.ProductRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;
import uz.master.warehouse.session.SessionUser;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService extends AbstractService<ProductRepository, ProductMapper> implements GenericCrudService<Product, ProductDto, ProductCreateDto, ProductUpdateDto, ProductCriteria, Long> {

    private final EntityManager entityManager;
    private final SessionUser session;


    public ProductService(
            ProductRepository repository,
            ProductMapper mapper,
            EntityManager entityManager, SessionUser session) {
        super(repository, mapper);
        this.entityManager = entityManager;
        this.session = session;
    }

    @Override
    public DataDto<Long> create(ProductCreateDto createDto) {
        Product product = mapper.fromCreateDto(createDto);
        product.setColor(createDto.getColor());
        product.setDefault_price(createDto.getDefault_price());
        product.setModel(createDto.getModel());
        product.setFirmId(createDto.getFirmId());
        product.setItem_count(createDto.getItem_count());
        product.setOrgId(session.getOrgId());
        Product save = repository.save(product);
        return new DataDto<>(save.getId());
    }

    @Override
    public DataDto<Void> delete(Long id) {
        try {
            repository.deleteProduct(id, session.getOrgId());
            return new DataDto<>();
        } catch (Exception e) {
            throw new RuntimeException("Product Not Found");
        }
    }

    @Override
    public DataDto<Long> update(ProductUpdateDto updateDto) {

        Product product = mapper.fromUpdateDto(updateDto);
        product.setDefault_price(updateDto.getDefault_price());
        product.setSectorId(updateDto.getSectorId());
        Product save = repository.save(product);
        return new DataDto<>(save.getId());
    }

    @Override
    public DataDto<List<ProductDto>> getAll() {
        return new DataDto<>(mapper.toDto(repository.findAllByOrgIdAndDeletedFalse(session.getOrgId())));
    }

    @Override
    public DataDto<ProductDto> get(Long id) {
        Product product = repository.findByIdAndOrgIdAndDeletedFalse(id, session.getOrgId()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Not found");
        });
        return new DataDto<>(mapper.toDto(product));
    }

    @Override
    public DataDto<List<ProductDto>> getWithCriteria(ProductCriteria criteria) {
        List<ProductDto> products = new ArrayList<>();
        StringBuilder builder;

        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ware_house", "postgres", "Shoxruh0912");
            Statement statement = connection.createStatement();
            builder = new StringBuilder();
            builder.append("select * from product p where p.org_id = %s".formatted(session.getOrgId()));

            if (Objects.nonNull(criteria.getModel())) {
                builder.append("and p.model = '%s' ".formatted(criteria.getModel()));
            }
            if (Objects.nonNull(criteria.getFirmId())) {
                builder.append("and p.firm_id = %s ".formatted(criteria.getFirmId()));
            }
            if (Objects.nonNull(criteria.getColor())) {
                builder.append(" and p.color  =  %s ".formatted(criteria.getColor()));
            }
            ResultSet resultSet = statement.executeQuery(builder.toString());
            while (resultSet.next()) {
                ProductDto dto = ProductDto.builder()
                        .item_count(resultSet.getInt("item_count"))
                        .color(resultSet.getString("color"))
                        .model(resultSet.getString("model"))
                        .default_price(resultSet.getDouble("default_price"))
                        .firmId(resultSet.getLong("firm_id"))
                        .orgId(resultSet.getLong("org_id")).build();
                dto.setId(resultSet.getLong("id"));
                products.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DataDto<>(products);
    }

    @Transactional
    public DataDto<List<ProductDto>> search(String name) {
        SearchSession searchSession = Search.session(entityManager);
        SearchResult<Product> result = searchSession.search(Product.class)
                .where(f -> f.match()
                        .fields("model")
                        .matching(name))
                .fetch(20);

        long totalHitCount = result.total().hitCount();
        List<ProductDto> productDtos = mapper.toDto(result.hits());
        return new DataDto<>(productDtos, totalHitCount);
    }

    public DataDto<List<ProductDto>> getByFirmId(Long id) {
        return new DataDto<>(mapper.toDto(repository.findAllByOrgIdAndFirmId(id, session.getOrgId())));
    }
}
