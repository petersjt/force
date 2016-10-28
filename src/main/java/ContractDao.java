import java.util.List;

/**
 * Interface for the Contract Database Access Object
 */
public interface ContractDao {
    /**
     * select * from force.contract
     */
    public List<Contract> findAll();

    /**
     * select * from force.contract where contractId = "contractId"
     */
    public Contract findById();

    /**
     * insert into force.contract
     * values (contract.getAmountRequested(), contract.getBusinessNumber(), contract.getActivationDate(),
     * contract.getName(), contract.getStatus())
     */
    public boolean createContract(Contract contract);

    /**
     * update force.contract
     * set amountRequested = contract.getAmountRequested(), businessNumber = contract.getBusinessNumber(),
     * contractActivationDate = contract.getActivationDate(), name = contract.getName(), status = contract.getStatus()
     * where contractId = contract.getContractId()
     */
    public boolean updateContract(Contract contract);

    /**
     * delete from force.contract where contractId = contract.getContractId()
     */
    public boolean deleteContract(String contractId);
}
