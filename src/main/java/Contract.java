import java.math.BigDecimal;
import java.util.Date;

/**
 * Object for a Contract
 */
public class Contract
{
    public static BigDecimal EXPRESS_CONTRACT_CEILING = new BigDecimal(50000);

    // Assuming ContractId could be alpha-numeric
    private String contractId;
    private String name;
    private String businessNumber;
    private Date contractActivationDate;
    private BigDecimal amountRequested;
    private ContractStatus status;

    /**
     * Constructor
     */
    public Contract()
    {

    }

    /**
     * Specific Constructor
     *
     * @param contractId
     */
    public Contract(String contractId)
    {
        this.contractId = contractId;
        this.name = "Name";
    }

    /**
     * Returns the Contract Id
     *
     * @return String Contract Id
     */
    public String getContractId()
    {
        return contractId;
    }

    /**
     * Sets the Contract Id
     *
     * @param contractId String value that identifies a contract
     */
    public void setContractId(String contractId)
    {
        this.contractId = contractId;
    }

    /**
     * Returns the Name on the Contract
     *
     * @return String Name on the Contract
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the Name on the Contract
     *
     * @param name String Name for the Contract
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns the Business Number on the Contract
     *
     * @return String Business Number on the Contract
     */
    public String getBusinessNumber()
    {
        return businessNumber;
    }

    /**
     * Sets the Business Number on the Contract
     *
     * @param businessNumber String Business Number for the Contract
     */
    public void setBusinessNumber(String businessNumber)
    {
        this.businessNumber = businessNumber;
    }

    /**
     * Returns the Contract Activation Date for the Contract
     *
     * @return Date the Contract Activation Date for the Contract
     */
    public Date getContractActivationDate()
    {
        return contractActivationDate;
    }

    /**
     * Sets the Contract Activation Date for the Contract
     * @param contractActivationDate Date the Contract Activation Date for the Contract
     */
    public void setContractActivationDate(Date contractActivationDate)
    {
        this.contractActivationDate = contractActivationDate;
    }

    /**
     * Returns the Amount Requested for the Contract
     *
     * @return BigDecimal the Amount Requested for the Contract
     */
    public BigDecimal getAmountRequested()
    {
        return amountRequested;
    }

    /**
     * Sets the Amount Requested for the Contract
     *
     * @param amountRequested BigDecimal the Amount Requested for the Contract
     */
    public void setAmountRequested(BigDecimal amountRequested)
    {
        this.amountRequested = amountRequested;
    }

    /**
     * Returns whether the status is Accepted or Denied
     *
     * @return ContractStatus Accepted or Denied
     */
    public ContractStatus getStatus()
    {
        return status;
    }

    /**
     * Sets whether the status is Accepted or Denied
     *
     * @param status boolean Accepted or Denied
     */
    public void setStatus(ContractStatus status)
    {
        this.status = status;
    }
}
