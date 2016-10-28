import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for handling all requests for Contracts
 */
@RestController
public class ContractController
{
    public static String INVALID_FORMAT = "Data format is invalid.";

    /**
     * Returns a list of all available Contracts
     *
     * @return List of available Contracts
     */
    @RequestMapping(value = "/contract/", method = RequestMethod.GET)
    public ResponseEntity<List<Contract>> getApprovedContracts()
    {
        // ContractDao.findAll()
        List<Contract> contractList = new ArrayList<Contract>();
        Contract contract = new Contract("10");
        contractList.add(contract);

        contract = new Contract("12");
        contractList.add(contract);

        return new ResponseEntity<List<Contract>>(contractList, HttpStatus.OK);
    }

    /**
     * Returns the specified Contract given the Contract Id
     *
     * @param contractId    String value that identifies a contract
     * @return ResponseEntity for the Contract object and a Status Code
     */
    @RequestMapping(value = "/contract/{id}", method = RequestMethod.GET)
    public ResponseEntity getContract(@PathVariable("id") String contractId)
    {
        // Encrypt/Decrypt Id?
        // ContractDao.findById(contractId)

        // if(ContractDao.findById(contractId) == null)
        // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A contract with the id of " + contractId + " was not found. Please try again.");

        Contract contract = new Contract(contractId);
        return new ResponseEntity<Contract>(contract, HttpStatus.OK);
    }

    /**
     * Creates a Contract with the given information
     *
     * @param contract Contract details provided in the request
     * @return ResponseEntity with a Status Code
     */
    @RequestMapping(value = "/contract/", method = RequestMethod.POST)
    public ResponseEntity createContract(@RequestBody Contract contract)
    {
        if(contract == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_FORMAT);
        }

        try
        {
            // Create contract
            Contract newContract = new Contract(contract.getContractId());
            newContract.setAmountRequested(contract.getAmountRequested());
            newContract.setBusinessNumber(contract.getBusinessNumber());
            newContract.setName(contract.getName());

            // Determine if the Contract is Approved

            newContract = updateContractAmount(contract.getAmountRequested(), newContract);

            if(newContract == null)
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_FORMAT);
            }
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_FORMAT);
        }

        // ContractDao.createContract(contract)

        return ResponseEntity.status(HttpStatus.OK).body("Contract successfully created.");
    }

    /**
     * Updates the specified Contract using the provided Contract Id and the details from the Request Body
     *
     * @param contractId String value that identifies a contract
     * @param contract Contract details provided in the request
     * @return ResponseEntity with the updated Contract object and a Status Code
     */
    @RequestMapping(value = "/contract/{id}", method = RequestMethod.POST)
    public ResponseEntity updateContract(@PathVariable("id") String contractId, @RequestBody Contract contract)
    {
        if(contractId == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A contract with the id of " + contractId + " was not found. Please try again.");
        }
        else if(contract == null || contract.toString().isEmpty() || contract.getContractId() == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_FORMAT);
        }


        if(contract.getAmountRequested() != null)
        {
            contract = updateContractAmount(contract.getAmountRequested(), contract);
        }

        // ContractDao.updateContract(contract)
        return ResponseEntity.status(HttpStatus.OK).body("Contract " + contractId + " successfully updated.");
    }



    /**
     * Deletes the Contract with a Contract Id matching the one provided
     *
     * @param contractId String value that identifies a contract
     * @return ResponseEntity with a Status Code
     */
    @RequestMapping(value = "/contract/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteContract(@PathVariable(value="id") String contractId)
    {
        if(contractId == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A contract with the id of " + contractId + " was not found. Please try again.");
        }

        // ContractDao.deleteContract(contractId)
        return ResponseEntity.status(HttpStatus.OK).body("Contract " + contractId + " successfully deleted.");
    }

    /**
     * Updates the given Contract with the new Amount Requested.
     * Depending on the amount, the contract will be Approved or Denied. If Approved,
     * the Contract Activation Date will be updated to the current date.
     *
     * @param amount BigDecimal Amount Requested for the Contract
     * @param contract Contract the current Contract being updated
     * @return Contract the current Contract being updated
     */
    private Contract updateContractAmount(BigDecimal amount, Contract contract)
    {
        try
        {
            if(Contract.EXPRESS_CONTRACT_CEILING.compareTo(amount) < 0)
            {
                contract.setStatus(ContractStatus.APPROVED);
                contract.setContractActivationDate(Date.from(Instant.now()));
            }
            else
            {
                contract.setStatus(ContractStatus.DENIED);
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed to update contract. Full exception: " + e);
            return null;
        }

        return contract;
    }
}
