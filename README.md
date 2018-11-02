# epc-standards - implements the [GS1 EPC decoding standard](https://www.gs1.org/sites/default/files/docs/epc/GS1_EPC_TDS_i1_11.pdf).

[![][license img]][license]

epc-standards implements decoding of GS1 EPC tags.

Example:

``` java
import com.nike.epc.decode;

Epc epc = Decoding.decode("30185E78003ECB006E2DA00E");
Optional<Sgtin> sgtin = epc.scheme().sgtin();
```

Tags can be decoded from hex encoded bytes or raw bytes (`byte[]` or `ByteBuffer`).

Currently only SGTIN-96 and a custom Nike display tag format (XNDT) are supported. See [Issue 2](https://github.com/Nike-Inc/epc-standards/issues/2) for up to date progress on supporting more formats.

## Performance

A major goal of epc-standards is to be fast. We've included some basic benchmarks for SGTIN and XNDT tags. More will be added as support for more tag formats is added. Any tags that have a third party parsing library will be included in the benchmarks for comparision purposes. Currently SGTIN parsing in epc-standards is compared to SGTIN parsing from [epctagcoder](https://github.com/jlcout/epctagcoder).

### SGTIN parsing performance compared to epctagcoder

The graph below was produced using a mid 2015 MacBook Pro.

![benchmark][benchmark img]

Orange: epctagcoder

Blue: epc-standards

[license]:LICENSE
[license img]:https://img.shields.io/badge/License-Apache%202-blue.svg
[benchmark img]:benchmark.png
