pragma solidity ^0.4.23;

contract Resource {
    
    address public ca;
    
    function createContract(string resName) public constant returns (address resultCA) {
        NewContract ca = new NewContract(resName);
        return ca;
    }
}

contract NewContract {
    string public resourceName;
    
    constructor (string resName) {
        resourceName = resName;
    }
    
    function setResourceName(string resName) public returns (uint result) {
        resourceName = resName;
        return 1;
    }
    
    function getResourceName() public constant returns (string resName) {
        return resourceName;
    }
}

