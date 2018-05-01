pragma solidity ^0.4.19;

contract Resource {
    
    bytes32 resourceName;
    struct delegateStruct {
        address delegateAddr;
    }
    
    mapping(uint => delegateStruct) delStrMap;
    
    //getter/setter for resource
    function setResourceName(bytes32 resName) public returns (uint result) {
        resourceName = resName;
        return 1;
    }
    
    function getResourceName() public constant returns (bytes32 resName) {
        return (resourceName);
    }
}

