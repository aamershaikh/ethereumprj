pragma solidity ^0.4.19;

contract Resource {
    
    bytes32 resourceName;
    
    //getter/setter for resource
    function setResourceName(bytes32 resName) public returns (uint result) {
        resourceName = resName;
        return 1;
    }
    
    function getResourceName() public constant returns (bytes32 resName) {
        return (resourceName);
    }
}

contract Delegate {
    struct delegateStruct {
        address delegateAddr;
    }
    mapping(uint => delegateStruct) delStrMap;
    
    function setDelegateMap(uint key, address delAddr) public returns (bool result) {
        delStrMap[key].delegateAddr = delAddr;
        return true;
    }
    
    function getDelegateMap(uint key) public constant returns (address delAddr) {
        return (delStrMap[key].delegateAddr);
    }
} 
